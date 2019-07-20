package com.thoughtworks.parking_lot.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @title: ParkingOrderDTO
 * @projectName: parking_lot
 * @description: TODO
 * @author: macmanboy@foxmail.com(Gio Zhang)
 * @date: 2019/7/20 17:22
 */
public class ParkingOrderDTO {
    private Integer id;

    private String parkingLotName;

    private String carNumber;

    private Date createTime;//订单创建时间，默认订单创建之后 只会从“创建”到“结束”

    private Date updateTime;//订单结束时间

    private String status;

    private Integer parkingLotId;

    public ParkingOrderDTO(Integer id, String parkingLotName, String carNumber, Date createTime, Date updateTime, String status, Integer parkingLotId) {
        this.id = id;
        this.parkingLotName = parkingLotName;
        this.carNumber = carNumber;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
        this.parkingLotId = parkingLotId;
    }

    public ParkingOrderDTO(String parkingLotName, String carNumber, Date createTime, Date updateTime, String status, Integer parkingLotId) {
        this.parkingLotName = parkingLotName;
        this.carNumber = carNumber;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
        this.parkingLotId = parkingLotId;
    }

    public Integer getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Integer parkingLotId) {
        this.parkingLotId = parkingLotId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
