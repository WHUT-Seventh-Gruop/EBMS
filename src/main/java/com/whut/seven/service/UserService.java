package com.whut.seven.service;


import com.whut.seven.entity.User;

/**
 * @Author Zrt
 * @Date 2020/8/19 15:27
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param username 账号
     * @param password 密码(使用MD5加密
     * @param role     身份
     * @return 登录的用户信息
     */
    User login(String username, String password, Integer role);

    User login(String username, String password);

    /**
     * 注册用户
     * @param username 账号
     * @param password 密码（使用MD5加密
     * @param role 身份
     * @return 注册的用户信息
     */
    User register(String username, String password, Integer role);

    /**
     * 注册用户
     * @param user 注册的用户信息
     * @return 返回注册的信息
     */
    User register(User user);

    /**
     * 查找用户
     * @param username 账号
     * @return 返回用户信息
     */
    User findByName(String username);


    void changePassword(User user);
}
