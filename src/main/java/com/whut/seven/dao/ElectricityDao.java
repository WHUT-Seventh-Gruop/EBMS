package com.whut.seven.dao;

import com.whut.seven.entity.Electricity;
import com.whut.seven.entity.PayUnit;
import com.whut.seven.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @Author Zrt
 * @Date 2020/8/21 21:34
 */
public interface ElectricityDao extends JpaRepository<Electricity, String>, JpaSpecificationExecutor<Electricity> {

    Page<Electricity> findAllByUserAndPayTimeBetweenAndIsPay(User user, Date start, Date end, boolean isPay,  Pageable pageable);
    Page<Electricity> findAllByUserAndIsPay(User user, boolean isPay,Pageable pageable);


    Page<Electricity> findAllByPayUnitAndCreateTimeBetween(PayUnit payUnit, Date start, Date end, Pageable pageable);

    Electricity findElectricityById(String id);
}
