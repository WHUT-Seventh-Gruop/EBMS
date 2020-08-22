package com.whut.seven.dao;

import com.whut.seven.entity.Electricity;
import com.whut.seven.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * @Author Zrt
 * @Date 2020/8/21 21:34
 */
public interface ElectricityDao extends JpaRepository<Electricity, String> {

    Page<Electricity> findAllByUserAndCreateTimeBetween(User user, Date start, Date end, Pageable pageable);
    Page<Electricity> findAllByUser(User user,Pageable pageable);
}
