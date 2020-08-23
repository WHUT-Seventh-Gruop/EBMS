package com.whut.seven.service.impl;

import com.whut.seven.dao.PayUnitDao;
import com.whut.seven.service.PayUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayUnitServiceImpl implements PayUnitService {

    @Autowired
    PayUnitDao payUnitDao;

    @Override
    public List<String> findAllCampus() {
        return payUnitDao.findAllCampus();
    }

    @Override
    public List<String> findAllBuildingsByCampus(String campus) {
        return payUnitDao.findAllBuildingsByCampus(campus);
    }

    @Override
    public List<String> findAllDormitoriesByBuilding(String building) {
        return payUnitDao.findAllDormitoriesByBuilding(building);
    }
}
