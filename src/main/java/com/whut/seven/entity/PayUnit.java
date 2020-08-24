package com.whut.seven.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * 缴费单位表
 *
 * @Author Zrt
 * @Date 2020/8/20 21:32
 */
@Entity
@Table(name = "t_pay_unit")
public class PayUnit {
    /**
     * 缴费单位ID 主键 自增
     */
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 校区
     */
    private String campus;
    /**
     * 楼号
     */
    private String buildingNo;
    /**
     * 宿舍门牌号
     */
    private String dormitoryNo;
    /**
     * 每个单位对应的电费列表
     */
    @OneToMany(mappedBy = "payUnit")
    private List<Electricity> electricityList = new ArrayList<>();
    
    
    
    

    public List<Electricity> getElectricityList() {
        return electricityList;
    }

    public void setElectricityList(List<Electricity> electricityList) {
        this.electricityList = electricityList;
    }

    public PayUnit() {
    }

    @Override
    public String toString() {
        return "PayUnit{" +
                "id=" + id +
                ", campus='" + campus + '\'' +
                ", buildingNo='" + buildingNo + '\'' +
                ", dormitoryNo='" + dormitoryNo + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getDormitoryNo() {
        return dormitoryNo;
    }

    public void setDormitoryNo(String dormitoryNo) {
        this.dormitoryNo = dormitoryNo;
    }
}
