package com.hui.store.mapper;

import com.hui.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

//@SpringBootTest:表示标注当前的类时一个测试类，不会随同项目一块打包
@SpringBootTest
//RunWith:表示启动这个单元测试类（单元测试类是不能运行的），需要传递一个SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void insert(){
        /**
         * 单元测试方法： 不需要启动整个项目，可以做单元测试
         * 必须被@Test注解修饰
         * 返回值类型必须是void
         * 方法的参数列表不指定任何类型
         * 方法的访问修饰符必须是public
         */

        User user = new User();
        user.setUsername("tim");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }


    @Test
    public void findByUsername(){
        User user = userMapper.findByUsername("tim");
        System.out.println(user);
    }
}
