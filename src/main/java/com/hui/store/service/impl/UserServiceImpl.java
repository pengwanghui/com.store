package com.hui.store.service.impl;

import com.hui.store.entity.User;
import com.hui.store.mapper.UserMapper;
import com.hui.store.service.IUserService;
import com.hui.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        String username = user.getUsername();
        User result = userMapper.findByUsername(username);
        if(result!=null){
            throw new UsernameDuplicatedException("用户名被占用");
        }

        //密码加密处理的实现：md5
        //串 +password +串 ---md5算法加密，连续加载三次
        //盐值 +password +盐值 ---盐值就是一个随机的字符串
        String oldPassword = user.getPassword();
        //获取盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        //盐值记录
        user.setSalt(salt);
        //加密处理
        String md5Password = getMD5Password(oldPassword, salt);
        user.setPassword(md5Password);




        //补全数据：is_delete设置为0
        user.setIsDelete(0);

        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);


        //执行注册业务功能的实现
        Integer rows = userMapper.insert(user);
        if(rows!=1){
            throw new InsertException("在用户注册过程中产生了未知的异常");
        }
    }

    @Override
    public User login(String username, String password) {
        //根据用户名称来查询用户的数据是否存在，如果不存在，抛出异常
        User result = userMapper.findByUsername(username);
        if(result == null){
            throw new UserNotFoundException("用户数据不存在");
        }
        //检测用户的密码是否匹配
        String oldPassword = result.getPassword();
        String salt = result.getSalt();
        String newMd5Password = getMD5Password(password, salt);
        if(!newMd5Password.equals(oldPassword)){
            throw new PasswordNotMatchException("密码不正确");
        }

        if(result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }

        //User user = userMapper.findByUsername(username);
        //调用mapper层的findByUsername来查询用户的数据，来提升系统的性能
        //其他字段在这里用不到，所以可以进行数据压缩
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());

        return result;

    }

    @Override
    public void changePassword(Integer uid,
                               String username,
                               String oldPassword,
                               String newPassword)  {
        User result = userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        //原始密码和数据库中密码进行比较
        String oldMd5Password = getMD5Password(oldPassword, result.getSalt());
        if(!result.getPassword().equals(oldMd5Password)){
            throw new PasswordNotMatchException("密码错误");
        }
         //将新的密码设置到数据库中
        String newMd5Password = getMD5Password(newPassword, result.getSalt());
        //新密码和老密码不能一致
        if(newMd5Password.equals(oldMd5Password)){
            throw new PasswordError("新密码和老密码不允许一样！");
        }
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
        if(rows!=1){
            throw new UpdateException("更新数据时产生未知的异常");
        }

    }

    private String getMD5Password(String password,String salt){
        //MD5加密算法方法的调用
        for(int i = 0;i < 3;i++){
             password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }


}
