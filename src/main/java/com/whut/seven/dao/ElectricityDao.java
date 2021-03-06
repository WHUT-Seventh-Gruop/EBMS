package com.whut.seven.dao;

import com.whut.seven.entity.Electricity;
import com.whut.seven.entity.PayUnit;
import com.whut.seven.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @Author Zrt
 * @Date 2020/8/21 21:34
 */
public interface ElectricityDao extends JpaRepository<Electricity, String>, JpaSpecificationExecutor<Electricity> {

    Page<Electricity> findAllByUserAndPayTimeBetweenAndIsPay(User user, Date start, Date end, boolean isPay,  Pageable pageable);
    Page<Electricity> findAllByUserAndIsPay(User user, boolean isPay,Pageable pageable);


    Page<Electricity> findAllByPayUnitAndCreateTimeBetween(PayUnit payUnit, Date start, Date end, Pageable pageable);

    Electricity findElectricityById(String id);

    /**
     * 计算所有的电力消耗
     * SELECT * FROM 表名 WHERE DATE_FORMAT( 时间字段名, ‘%Y%m' ) = DATE_FORMAT( CURDATE( ) , ‘%Y%m' )
     * @return 本月的所有电力消耗
     */
    @Query(nativeQuery = true , value = "select sum(e.electricity_consumption) from t_electricity e where DATE_FORMAT(e.create_time, '%Y%m' )  = DATE_FORMAT( DATE_ADD(CURDATE( ),INTERVAL -1 MONTH) , '%Y%m' )")
    double calSumElectricityConsumption();

    /**
     * 计算本月的所有电费
     * @return 本月的所有电费
     */
    @Query(nativeQuery = true , value = "select sum(e.electric_charge) from t_electricity e where DATE_FORMAT(e.create_time, '%Y%m' )  = DATE_FORMAT( DATE_ADD(CURDATE( ),INTERVAL -1 MONTH), '%Y%m' )")
    double calSumElectricityCharge();


}
