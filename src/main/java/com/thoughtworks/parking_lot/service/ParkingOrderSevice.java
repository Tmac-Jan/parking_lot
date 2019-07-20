package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.dto.ParkingOrderDTO;
import com.thoughtworks.parking_lot.entity.ParkingOrder;

/**
 * @title: ParkingOrderSevice
 * @projectName: parking_lot
 * @description: TODO
 * @author: macmanboy@foxmail.com(Gio Zhang)
 * @date: 2019/7/20 18:37
 */
public interface ParkingOrderSevice {

    ParkingOrder finishParkingOrder(Integer parkingOrderId);
}
