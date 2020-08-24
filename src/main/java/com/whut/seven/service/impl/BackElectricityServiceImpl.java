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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Page<Electricity> findAllElectricity(Pageable pageable, String username, String campus) {
        return electricityDao.findAll(new Specification<Electricity>() {
            @Override
            public Predicate toPredicate(Root<Electricity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                //存放查询条件
                List<Predicate> predicates = new ArrayList<Predicate>();
                if(username!=null &&  !"".equals(username) ){
                    predicates.add(cb.like(root.<User>get("user").get("username"), "%" + username + "%"));
                }
                if(campus!=null){
                    predicates.add(cb.like(root.<PayUnit>get("payUnit").get("campus"), "%" + campus + "%"));
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
    public void deleteElectricityById(String id) {
        electricityDao.deleteById(id);
    }

    /**
     * 根据ID查找账单
     *
     * @param id 账单id
     * @return 查找到的信息
     */
    @Override
    public Electricity findById(String id) {
        return electricityDao.findElectricityById(id);
    }

    /**
     * 计算本月的电费
     *
     * @return 电费的值
     */
    @Override
    public Double calSumElectricityConsumptionThisMonth() {
        double v = electricityDao.calSumElectricityConsumption();
        return v;
    }

    /**
     * 计算本月的所有电费
     *
     * @return 本月电费总和
     */
    @Override
    public Double calSumElectricityChargeThisMonth() {
        return electricityDao.calSumElectricityCharge();
    }

    @Override
    public Map<Long,Double> findElectricityConsumptionGroupByUnit() {
        List<Electricity> all = electricityDao.findAll();
        Map<Long,Double> result = new HashMap();
        for (Electricity electricity : all) {
            PayUnit payUnit = electricity.getPayUnit();
            if(result.containsKey(payUnit.getId())){
                Double aDouble = result.get(payUnit.getId());
                double v = electricity.getElectricCharge().doubleValue();
                double v1 = aDouble + v;
                result.put(payUnit.getId(),v1);
            }else{
                result.put(payUnit.getId(),electricity.getElectricityConsumption().doubleValue());
            }
        }
        return result;
    }
}
