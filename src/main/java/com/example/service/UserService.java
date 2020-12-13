package com.example.service;

import com.example.pojo.User;

import java.util.List;

public interface UserService {
    //根据Id查询用户
    User queryUserById(int userId);

    //根据激活码查询用户
    User queryUserByActiveCode(String activeCode);

    //根据邮箱密码与状态码登录
    User loginByUserNameAndPasswordAndActiveStatus(User user);

    //根据邮箱密码与Role=1登录
    User AdminLogin(User user);

    //增加用户
    int addUser(User user);

    //删除用户
    int deleteUser(Integer userId);

    // 修改用户
    int updateUser(User user);

    //查询用户
    List<User> queryAllUser();

    User findByUserName(String userName);

    User findByUserNameAndEmail(User user);

}
