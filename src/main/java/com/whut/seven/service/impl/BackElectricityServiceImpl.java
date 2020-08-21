package com.whut.seven.service.impl;

import com.whut.seven.dao.ElectricityDao;
import com.whut.seven.entity.Electricity;
import com.whut.seven.service.BackElectricityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Zrt
 * @Date 2020/8/21 21:41
 */
@Service
public class BackElectricityServiceImpl implements BackElectricityService {
    @Autowired
    ElectricityDao electricityDao;
    /**
     * 添加电费账单
     *
     * @param electricity 电费信息
     * @return 电费信息
     */
    @Override
    public Electricity addElectricity(Electricity electricity) {
        return electricityDao.save(electricity);
    }

    /**
     * 更新电费账单
     *
     * @param electricity 电费信息
     * @return 电费信息
     */
    @Override
    public Electricity updateElectricity(Electricity electricity) {
        return electricityDao.save(electricity);
    }

    /**
     * 查找所有电费账单
     *
     * @return 电费信息
     */
    @Override
    public List<Electricity> findAllElectricity() {
        return electricityDao.findAll();
    }

    /**
     * 根据ID删除电费账单
     *
     * @param id 电费信息ID
     * @return 电费信息
     */
    @Override
    public Electricity deleteElectricityById(String id) {
        return deleteElectricityById(id);
    }
}
