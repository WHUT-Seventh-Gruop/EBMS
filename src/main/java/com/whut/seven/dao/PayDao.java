package com.whut.seven.dao;

import com.whut.seven.entity.Electricity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author:SUN HAOKAI
 */
public interface PayDao  extends JpaRepository<Electricity, Long>, JpaSpecificationExecutor<Electricity> {
}
