package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.entity.ParkingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @title: ParkingOrderRepository
 * @projectName: parking_lot
 * @description: TODO
 * @author: macmanboy@foxmail.com(Gio Zhang)
 * @date: 2019/7/20 16:01
 */
public interface ParkingOrderRepository extends JpaRepository<ParkingOrder,Integer> {

}
