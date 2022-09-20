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

//@SpringBootTest:表示标注当前的类时一个测试类，不会随同项目一块打包
@SpringBootTest
//RunWith:表示启动这个单元测试类（单元测试类是不能运行的），需要传递一个SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Resource
    private IUserService userService;

    @Resource
    private UserMapper userMapper;

    @Test
    public void reg(){
        /**
         * 单元测试方法： 不需要启动整个项目，可以做单元测试
         * 必须被@Test注解修饰
         * 返回值类型必须是void
         * 方法的参数列表不指定任何类型
         * 方法的访问修饰符必须是public
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
        userMapper.updatePasswordByUid(6,"321","管理员",new Date());
    }

    @Test
    public void findByUid(){

    }

    @Test
    public void changePassword(){
        userService.changePassword(7,"管理员","123","123456");
    }


}
