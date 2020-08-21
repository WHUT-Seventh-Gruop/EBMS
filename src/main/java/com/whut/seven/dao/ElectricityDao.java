package com.whut.seven.dao;

import com.whut.seven.entity.Electricity;
import com.whut.seven.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Zrt
 * @Date 2020/8/21 21:34
 */
public interface ElectricityDao extends JpaRepository<Electricity, String> {
}
