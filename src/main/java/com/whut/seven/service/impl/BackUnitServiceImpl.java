package com.whut.seven.service.impl;

import com.whut.seven.dao.BackUnitDao;
import com.whut.seven.dao.PayUnitDao;
import com.whut.seven.entity.PayUnit;
import com.whut.seven.service.BackUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Zrt
 * @Date 2020/8/24 9:12
 */
@Service
public class BackUnitServiceImpl implements BackUnitService {
    @Autowired
    BackUnitDao backUnitDao;
    /**
     * 根据ID查找缴费单位
     *
     * @param id 支付单元id
     * @return
     */
    @Override
    public PayUnit findUnitById(String id) {
        return backUnitDao.getOne(new Long(id));
    }

    /**
     * 查找所有缴费单位
     *
     * @return
     */
    @Override
    public List<PayUnit> findAllUnit() {
        return backUnitDao.findAll();
    }

    /**
     * 计算所有的单位数量
     *
     * @return 数量
     */
    @Override
    public Long countUnit() {
        return backUnitDao.count();
    }
}
