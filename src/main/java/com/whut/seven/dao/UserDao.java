package com.whut.seven.dao;

import com.whut.seven.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Zrt
 * @Date 2020/8/19 15:25
 */
public interface UserDao extends JpaRepository<User, String> {

    /**
     * 登陆
     * 根据账号密码查找user
     *
     * @param username 账号
     * @param password 密码
     * @return 用户信息
     */
    User findUserByUsernameAndPassword(String username, String password);

    /**
     * 根据账号密码身份查找用户
     *
     * @param username 账号
     * @param password 密码
     * @param role     身份
     * @return 用户信息
     */
    User findUserByUsernameAndPasswordAndRole(String username, String password, Integer role);

    /**
     * 根据用户名查找用户
     * @param userName 用户名
     * @return 用户信息
     */
    User findUserByUsername(String userName);

    /**
     * 根据用户名删除用户
     * @param userName 用户名
     * @return 删除的用户信息
     */
    User deleteByUsername(String userName);

}
