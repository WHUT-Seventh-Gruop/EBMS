package com.whut.seven.service.impl;

import com.whut.seven.dao.UserDao;
import com.whut.seven.entity.User;
import com.whut.seven.service.BackUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Zrt
 * @Date 2020/8/21 21:00
 */
@Service
public class BackUserServiceImpl implements BackUserService {
    @Autowired
    private UserDao userDao;
    /**
     * 修改自己的密码
     *
     * @param username 用户名
     * @param password 自己的密码
     * @return 修改后的信息
     */
    @Override
    public User changeSelfPassword(String username, String password) {
        User userByUsername = userDao.findUserByUsername(username);
        userByUsername.setPassword(password);
        return userDao.save(userByUsername);
    }

    /**
     * 超级管理员 添加管理员
     *
     * @param username 用户名
     * @param password 密码
     * @param role     身份
     * @return 说添加的信息
     */
    @Override
    public User addAdmin(String username, String password, Integer role) {
        User user = new User(username,password,role);
        return userDao.save(user);
    }

    /**
     * 根据用户名进行模糊查询，如果用户名为空则全查
     * @param pageable 分页对象
     * @param username 用户名
     * @return 所有的管理员信息
     */
    @Override
    public Page<User> findAllAdmin(Pageable pageable,String username) {
        return userDao.findAll(new Specification<User>(){
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                //存放查询条件
                List<Predicate> predicates = new ArrayList<Predicate>();
                predicates.add(cb.equal(root.<User>get("role"), 2));
                if(username!=null &&  !"".equals(username) ){
                    predicates.add(cb.like(root.<String>get("username"), "%" + username + "%"));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    /**
     * 保存管理员
     *
     * @param user 管理员信息
     * @return 被保存的管理员信息
     */
    @Override
    public User saveAdmin(User user) {
        return userDao.save(user);
    }

    /**
     * 根据用户名删除用户信息
     *
     * @param username 用户名
     * @param role     用户的类别
     * @return 被删除的信息
     */
    @Override
    public User deleteAdminByUsername(String username, Integer role) {
        return userDao.deleteByUsername(username);
    }

    /**
     * 根据用户名查找用户
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @Override
    public User findByUsername(String userName) {
        return userDao.findUserByUsername(userName);
    }
}
