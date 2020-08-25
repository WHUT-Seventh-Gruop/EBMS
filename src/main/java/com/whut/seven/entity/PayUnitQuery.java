package com.whut.seven.entity;

import java.util.Date;

public class PayUnitQuery {
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

    private String date;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PayUnitQuery{" +
                "id=" + id +
                ", campus='" + campus + '\'' +
                ", buildingNo='" + buildingNo + '\'' +
                ", dormitoryNo='" + dormitoryNo + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
