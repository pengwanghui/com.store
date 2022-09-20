package com.hui.store.controller;

import com.hui.store.service.ex.*;
import com.hui.store.utils.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 控制层类的基类
 */
public class BaseController {

    //操作成功的状态码
    public static final int ok = 200;

    //请求处理方法，这个方法的返回值就是需要传递给前端的数据
    //自动将异常对象传递给此方法的参数列表上
    //当前项目中产生了异常，被统一拦截到此方法中，此方法此时就充当的请求处理方法，方法的返回值直接发回给前端
    @ExceptionHandler(ServiceException.class)//用于统一处理抛出的异常
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<Void>(e);
        if(e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名已经被占用");
        } else if(e instanceof InsertException){
            result.setState(5000);
            result.setMessage("注册时产生未知的异常");
        }
        else if(e instanceof UserNotFoundException){
            result.setState(5001);
            result.setMessage("用户数据不存在的异常");
        }
        else if(e instanceof PasswordNotMatchException){
            result.setState(5002);
            result.setMessage("用户的密码错误的异常");
        }else if(e instanceof UpdateException){
            result.setState(5003);
            result.setMessage("更新数据时产生未知的异常");
        }else if (e instanceof PasswordError){
            result.setState(5004);
            result.setMessage("新密码和老密码不允许一样");
        }

        return  result;
    }

    /**
    * @Param: [session] session对象
    * @return: java.lang.Integer
    * @Author: Pwh
    * @Date: 2022/9/10
    */

    protected final Integer getUidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }
    /**
     * 获取session中的用户名
    * @Param: [session]
    * @return: java.lang.String
    * @Author: Pwh
    * @Date: 2022/9/10
    */

    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }

}
