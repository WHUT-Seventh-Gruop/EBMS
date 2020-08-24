package com.whut.seven.dao;

import com.whut.seven.entity.Electricity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * @author:SUN HAOKAI
 */
public interface PayDao  extends JpaRepository<Electricity, Long>, JpaSpecificationExecutor<Electricity> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE t_electricity SET is_pay = true, user_username = ?2  WHERE id = ?1"  , nativeQuery = true)
    void electricityGetPaid(String id, String username);
}
