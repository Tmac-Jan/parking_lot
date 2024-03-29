package com.thoughtworks.parking_lot.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * @title: ParkingOrder
 * @projectName: parking_lot
 * @description: TODO
 * @author: macmanboy@foxmail.com(Gio Zhang)
 * @date: 2019/7/20 15:41
 */
@Entity
@DynamicUpdate
public class ParkingOrder {
    @Id
    @GeneratedValue
    private Integer id;

    private String parkingLotName;

    private String carNumber;

    private Date createTime;//订单创建时间，默认订单创建之后 只会从“创建”到“结束”

    private Date updateTime;//订单结束时间

    private String status;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private ParkingLot parkingLot;

    public ParkingOrder(String parkingLotName, String carNumber) {
        this.parkingLotName = parkingLotName;
        this.carNumber = carNumber;
    }
    public ParkingOrder(){

    }
    public ParkingOrder(Integer id,String parkingLotName, String carNumber, Date createTime, Date updateTime, String status,ParkingLot parkingLot) {
        this.id=id;
        this.parkingLotName = parkingLotName;
        this.carNumber = carNumber;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
        this.parkingLot = parkingLot;
    }
    public ParkingOrder(Integer id,String parkingLotName, String carNumber, Date createTime, Date updateTime, String status) {
        this.id=id;
        this.parkingLotName = parkingLotName;
        this.carNumber = carNumber;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}
