package com.whut.seven.service.impl;

import com.whut.seven.dao.ElectricityDao;
import com.whut.seven.entity.Electricity;
import com.whut.seven.entity.User;
import com.whut.seven.service.ElectricityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectricityServiceImpl implements ElectricityService {

    @Autowired
    ElectricityDao electricityDao;

    @Override
    public Page<Electricity> findAllElectricity(User user, Pageable pageable) {
        return electricityDao.findAllByUser(user,pageable);
    }
}
