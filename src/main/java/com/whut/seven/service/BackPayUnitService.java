package com.whut.seven.service;

import com.whut.seven.entity.PayUnit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BackPayUnitService {
    /**
     * 添加缴费单位
     * @param payUnit 缴费单位
     * @return 电费信息
     */
    PayUnit addPayUnit(PayUnit payUnit);

    /**
     * 更新缴费单位
     * @param payUnit 缴费单位信息
     * @return 缴费单位
     */
    PayUnit updatePayUnit(PayUnit payUnit);
    
    /**
     * 查找所有缴费单位
     * @return 缴费单位信息
     */
    Page<PayUnit> findAllPayUnit(Pageable pageable);

    /**
     * 根据ID删除缴费单位信息
     * @param id 缴费单位ID
     * @return 缴费单位
     */
   void deletePayUnitById(Long id);

    List<String> listCampus();

  
    
    List<String> listBuildingNo();

    Page<PayUnit> listPayUnit(Pageable pageable, PayUnit payUnit);

    PayUnit getPayUnitByID(Long id);


}
