package com.whut.seven.dao;

import com.whut.seven.entity.PayUnit;
import com.whut.seven.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Zrt
 * @Date 2020/8/24 9:18
 */
public interface BackUnitDao extends JpaRepository<PayUnit, Long> {

}
