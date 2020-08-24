package com.whut.seven.service.impl;

import com.whut.seven.dao.ElectricityDao;
import com.whut.seven.dao.PayUnitDao;
import com.whut.seven.entity.Electricity;
import com.whut.seven.entity.PayUnit;
import com.whut.seven.entity.User;
import com.whut.seven.service.ElectricityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ElectricityServiceImpl implements ElectricityService {

    @Autowired
    ElectricityDao electricityDao;

    @Autowired
    PayUnitDao payUnitDao;

    @Override
    public Page<Electricity> findAllElectricityByIsPay(User user, Pageable pageable, boolean isPay) {
        return electricityDao.findAllByUserAndIsPay(user,isPay,pageable);
    }

    @Override
    public Page<Electricity> findAllElectricityByTimeAndIsPay(User user, Pageable pageable, Date date1, Date date2, boolean isPay) {
        return electricityDao.findAllByUserAndPayTimeBetweenAndIsPay(user,date1,date2,isPay,pageable);
    }

    @Override
    public Page<Electricity> findAllElectricity(Pageable pageable, PayUnit payUnit, Date date1, Date date2) {
        PayUnit payUnit1 = payUnitDao.findByCampusAndBuildingNoAndDormitoryNo(payUnit.getCampus(),payUnit.getBuildingNo(),payUnit.getDormitoryNo());
        return electricityDao.findAllByPayUnitAndCreateTimeBetween(payUnit1,date1,date2,pageable);
    }


}
