package com.hui.store.mapper;

import com.hui.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

//@SpringBootTest:��ʾ��ע��ǰ����ʱһ�������࣬������ͬ��Ŀһ����
@SpringBootTest
//RunWith:��ʾ���������Ԫ�����ࣨ��Ԫ�������ǲ������еģ�����Ҫ����һ��SpringRunner��ʵ������
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void insert(){
        /**
         * ��Ԫ���Է����� ����Ҫ����������Ŀ����������Ԫ����
         * ���뱻@Testע������
         * ����ֵ���ͱ�����void
         * �����Ĳ����б�ָ���κ�����
         * �����ķ������η�������public
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
