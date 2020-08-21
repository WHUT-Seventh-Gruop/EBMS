package com.whut.seven.service;

import com.whut.seven.entity.User;

import java.util.List;

/**
 * @Author Zrt
 * @Date 2020/8/21 20:52
 */
public interface BackUserService {

    /**
     * 修改自己的密码
     * @param username 用户名
     * @param password 自己的密码
     * @return 修改后的信息
     */
    User changeSelfPassword(String username, String password);

    /**
     * 超级管理员 添加管理员
     * @param username 用户名
     * @param password 密码
     * @param role 身份
     * @return 说添加的信息
     */
    User addAdmin(String username, String password,Integer role);

    /**
     * 超级管理员查询所有的管理员
     * @return 所有的管理员信息
     */
    List<User> findAllAdmin();

    /**
     * 保存管理员
     * @param user 管理员信息
     * @return 被保存的管理员信息
     */
    User saveAdmin(User user);

    /**
     * 根据用户名删除用户信息
     * @param username 用户名
     * @param role 用户的类别
     * @return 被删除的信息
     */
    User deleteAdminByUsername(String username,Integer role);

    /**
     * 根据用户名查找用户
     * @param userName 用户名
     * @return 用户信息
     */
    User findByUsername(String userName);

}
