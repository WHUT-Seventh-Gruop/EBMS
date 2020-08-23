package com.whut.seven.service.impl;

import com.whut.seven.dao.ElectricityDao;
import com.whut.seven.entity.Electricity;
import com.whut.seven.entity.PayUnit;
import com.whut.seven.entity.User;
import com.whut.seven.service.BackElectricityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
    public Page<Electricity> findAllElectricity(Pageable pageable, String username, Long unitId) {
        return electricityDao.findAll(new Specification<Electricity>() {
            @Override
            public Predicate toPredicate(Root<Electricity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                //存放查询条件
                List<Predicate> predicates = new ArrayList<Predicate>();
                if(username!=null &&  !"".equals(username) ){
                    predicates.add(cb.like(root.<User>get("user").get("username"), "%" + username + "%"));
                }
                if(unitId!=null){
                    predicates.add(cb.like(root.<PayUnit>get("payUnit").get("id"), "%" + unitId + "%"));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
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
