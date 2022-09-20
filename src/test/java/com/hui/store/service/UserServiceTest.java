package com.hui.store.service;

import com.hui.store.entity.User;
import com.hui.store.mapper.UserMapper;
import com.hui.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

//@SpringBootTest:��ʾ��ע��ǰ����ʱһ�������࣬������ͬ��Ŀһ����
@SpringBootTest
//RunWith:��ʾ���������Ԫ�����ࣨ��Ԫ�������ǲ������еģ�����Ҫ����һ��SpringRunner��ʵ������
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Resource
    private IUserService userService;

    @Resource
    private UserMapper userMapper;

    @Test
    public void reg(){
        /**
         * ��Ԫ���Է����� ����Ҫ����������Ŀ����������Ԫ����
         * ���뱻@Testע������
         * ����ֵ���ͱ�����void
         * �����Ĳ����б�ָ���κ�����
         * �����ķ������η�������public
         */

        try {
            User user = new User();
            user.setUsername("test001");
            user.setPassword("123");
            userService.reg(user);
            System.out.println("ok");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());

        }
    }

    @Test
    public void login(){
        User user = userService.login("test003", "123");
        System.out.println(user);
    }

    @Test
    public void updatePasswordByUid(){
        userMapper.updatePasswordByUid(6,"321","����Ա",new Date());
    }

    @Test
    public void findByUid(){

    }

    @Test
    public void changePassword(){
        userService.changePassword(7,"����Ա","123","123456");
    }


}
