package com.hui.store.controller;

import com.hui.store.entity.User;
import com.hui.store.service.IUserService;
import com.hui.store.service.ex.InsertException;
import com.hui.store.service.ex.UsernameDuplicatedException;
import com.hui.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController  //等价与 Controller+ResponseBody
@RequestMapping("users")
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;

    /*@RequestMapping("reg")
    public JsonResult<Void> reg(User user){
        JsonResult<Void> result = new JsonResult<>();
        try {
            userService.reg(user);
            result.setState(200);
            result.setMessage("用户注册成功");
        } catch (UsernameDuplicatedException e) {
            result.setState(4000);
            result.setMessage("用户名被占用");
        } catch (InsertException e){
            result.setState(5000);
            result.setMessage("注册时产生未知的异常");
        }

        return result;

    }*/

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){

            userService.reg(user);

            return new JsonResult<>(ok);
    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User data = userService.login(username, password);
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());


        System.out.println(getUidFromSession(session));
        System.out.println(getUsernameFromSession(session));
        return new JsonResult<>(ok,data);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,
                                           String newPassword,
                                           HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);

        return new JsonResult<>(ok);
    }




}
