package com.hui.store.mapper;

import com.hui.store.entity.User;

import java.util.Date;

/**
 * 用户模块的持久层接口
 */
public interface UserMapper {
    /***
    * @Param: [user] 用户的数据
    * @return: 受影响的行数（增删改都以受影响的行数作为返回值），根据返回值来确定是否操作成功
    * @Author: Pwh
    * @Date: 2022/9/6
    */
    Integer insert(User user);
    /***
    * @Param: [username] 用户名
    * @return: 如果找到对应的用户则返回这个用户的数据，没找到则返回null值
    * @Author: Pwh
    * @Date: 2022/9/6
    */

    User findByUsername(String username);

    /**根据用户的uid来修改用户密码
    * @Param: [uid] 用户的id
    * @return: java.lang.Integer 返回受影响的行数
    * @Author: Pwh
    * @Date: 2022/9/11
    */

    Integer updatePasswordByUid(Integer uid,
                                String password,
                                String modifiedUser,
                                Date modifiedTime);

    /**根据用户id查询用户的数据
    * @Param: [uid用户的id
    * @return: com.hui.store.entity.User 找到则返回对象，反之返回null值
    * @Author: Pwh
    * @Date: 2022/9/11
    */

    User findByUid(Integer uid);
}
