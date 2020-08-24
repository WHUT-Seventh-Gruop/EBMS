package com.whut.seven.service.impl;
import com.whut.seven.dao.BackPayUnitDao;
import com.whut.seven.entity.PayUnit;
import com.whut.seven.service.BackPayUnitService;
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

@Service
public class BackPayUnitServiceImpl implements BackPayUnitService {
    @Autowired
    BackPayUnitDao backPayUnitDao;

    @Override
    public PayUnit addPayUnit(PayUnit payUnit) {
        return this.backPayUnitDao.save(payUnit);
    }

    @Override
    public PayUnit updatePayUnit(PayUnit payUnit) {
        return  this.backPayUnitDao.save(payUnit);
    }

    @Override
    public Page<PayUnit> findAllPayUnit(Pageable pageable) {
        return this.backPayUnitDao.findAll(pageable);
    }

    @Override
    public void deletePayUnitById(Long id) {
        backPayUnitDao.deleteById(id);
    }

    
    
    
    @Override
    public List<String> listCampus() {
        return this.backPayUnitDao.listCampus();
    }
    
    @Override
    public List<String> listBuildingNo() {
        return this.backPayUnitDao.listBuildingNo();
    }

    @Override
    public Page<PayUnit> listPayUnit(Pageable pageable, PayUnit payUnit) {
     return backPayUnitDao.findAll(new Specification<PayUnit>() {
         @Override
         public Predicate toPredicate(Root<PayUnit> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
             List<Predicate> predicates =new ArrayList<Predicate>();
             if(payUnit.getId()!=null){
                 predicates.add((criteriaBuilder.equal(root.<Long>get("id"), payUnit.getId())));
             }else {
                 if (payUnit.getCampus() != null && !"".equals(payUnit.getCampus())) {
                     predicates.add((criteriaBuilder.equal(root.<String>get("campus"), payUnit.getCampus())));
                 }

                 if (payUnit.getBuildingNo() != null && !"".equals(payUnit.getBuildingNo())) {
                     predicates.add((criteriaBuilder.equal(root.<String>get("buildingNo"), payUnit.getBuildingNo())));
                 }
                 if (!"".equals(payUnit.getDormitoryNo()) && payUnit.getDormitoryNo() != null) {
                     predicates.add(criteriaBuilder.like(root.<String>get("dormitoryNo"), "%" + payUnit.getDormitoryNo() + "%"));
                 }
             }

             query.where(predicates.toArray(new Predicate[predicates.size()])); 
             return null;
         }
     },pageable);
    }

    @Override
    public PayUnit getPayUnitByID(Long id) {
        return backPayUnitDao.getOne(id);
    }
    
}
