package com.whut.seven.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Zrt
 * @Date 2020/8/20 21:36
 */
@Entity
@Table(name = "t_electricity")
public class Electricity {
    /**
     * 缴费单位ID 主键 自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 账单创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    /**
     * 用电量
     */
    private BigDecimal electricityConsumption;
    /**
     * 电费
     */
    private BigDecimal electricCharge;
    /**
     * 是否已经支付
     * true为已支付
     * false为未支付
     */
    private Boolean isPay;
    /**
     * 电费支付时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date payTime;
    /**
     * 支付电费的用户
     */
    @ManyToOne
    private User user;
    /**
     * 当前账单对应的缴费单位
     */
    @ManyToOne
    PayUnit payUnit;


    public Electricity() {
    }

    @Override
    public String toString() {
        return "Electricity{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", electricityConsumption=" + electricityConsumption +
                ", electricCharge=" + electricCharge +
                ", isPay=" + isPay +
                ", payTime=" + payTime +
                ", user=" + user +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getElectricityConsumption() {
        return electricityConsumption;
    }

    public void setElectricityConsumption(BigDecimal electricityConsumption) {
        this.electricityConsumption = electricityConsumption;
    }

    public BigDecimal getElectricCharge() {
        return electricCharge;
    }

    public void setElectricCharge(BigDecimal electricCharge) {
        this.electricCharge = electricCharge;
    }

    public Boolean getPay() {
        return isPay;
    }

    public void setPay(Boolean pay) {
        isPay = pay;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
