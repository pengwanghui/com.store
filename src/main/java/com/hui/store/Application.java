package com.hui.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//MapperScan注解制定当前项目中的Mapper接口路径的位置，在项目启动的时候，会自动记载所有的接口
@MapperScan("com.hui.store.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
