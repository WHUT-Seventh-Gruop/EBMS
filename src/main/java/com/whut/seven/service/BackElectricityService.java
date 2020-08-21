package com.whut.seven.service;

import com.whut.seven.entity.Electricity;

import java.util.List;

/**
 * @Author Zrt
 * @Date 2020/8/21 21:36
 */
public interface BackElectricityService {
    /**
     * 添加电费账单
     * @param electricity 电费信息
     * @return 电费信息
     */
    Electricity addElectricity(Electricity electricity);

    /**
     * 更新电费账单
     * @param electricity 电费信息
     * @return 电费信息
     */
    Electricity updateElectricity(Electricity electricity);

    /**
     * 查找所有电费账单
     * @return 电费信息
     */
    List<Electricity> findAllElectricity();

    /**
     * 根据ID删除电费账单
     * @param id 电费信息ID
     * @return 电费信息
     */
    Electricity deleteElectricityById(String id);
}
