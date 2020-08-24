package com.whut.seven.service;

import com.whut.seven.entity.Electricity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * @param pageable 分页对象
     * @param username 用户名
     * @param unitId 缴费单位
     * @return 查询到的信息
     */
    Page<Electricity> findAllElectricity(Pageable pageable, String username, String campus);

    /**
     * 根据ID删除电费账单
     * @param id 电费信息ID
     * @return 电费信息
     */
    void deleteElectricityById(String id);

    /**
     * 根据ID查找账单
     * @param id 账单id
     * @return 查找到的信息
     */
    Electricity findById(String id);
}
