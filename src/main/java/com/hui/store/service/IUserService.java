package com.hui.store.service;

import com.hui.store.entity.User;

import javax.servlet.http.HttpServletResponse;

/**
 * 用户模块业务层接口
 */
public interface IUserService {
    void reg(User user);

    User login(String username,String password);

    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword);

}
