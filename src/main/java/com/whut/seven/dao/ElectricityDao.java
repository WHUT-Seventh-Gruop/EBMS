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

    Page<Electricity> findAllByUserAndPayTimeBetweenAndIsPay(User user, Date start, Date end, boolean isPay,  Pageable pageable);
    Page<Electricity> findAllByUserAndIsPay(User user, boolean isPay,Pageable pageable);
}
