package com.whut.seven.service;

import com.whut.seven.entity.PayUnit;

import java.util.List;

/**
 * @Author Zrt
 * @Date 2020/8/24 9:10
 */
public interface BackUnitService {
    /**
     * 根据ID查找缴费单位
     * @param id
     * @return
     */
    PayUnit findUnitById(String id);

    /**
     * 查找所有缴费单位
     * @return
     */
    List<PayUnit> findAllUnit();
}
