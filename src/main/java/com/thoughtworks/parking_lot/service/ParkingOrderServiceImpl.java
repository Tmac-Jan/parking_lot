package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.dto.ParkingOrderDTO;
import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.entity.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @title: ParkingOrderServiceImpl
 * @projectName: parking_lot
 * @description: TODO
 * @author: macmanboy@foxmail.com(Gio Zhang)
 * @date: 2019/7/20 18:37
 */
@Service
public class ParkingOrderServiceImpl implements ParkingOrderSevice {

    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Autowired
    private ParkingOrderRepository parkingOrderRepository;

    @Override
    public ParkingOrder createParkingOrder(ParkingOrderDTO parkingOrderDTO) {
            Optional<ParkingLot> parkingLot = parkingLotRepository.findById(parkingOrderDTO.getParkingLotId());
            if (parkingLot.get() != null && parkingLot.get().getCapacity() > 0) {
                ParkingOrder parkingOrder = new ParkingOrder();
                BeanUtils.copyProperties(parkingOrderDTO, parkingOrder);
                parkingLot.get().setCapacity(parkingLot.get().getCapacity() - 1);
                return parkingOrderRepository.save(parkingOrder);
            } else {
                return null;
            }

    }

    @Override
    public ParkingOrder finishParkingOrder(Integer parkingOrderId) {
        Optional<ParkingOrder> parkingOrder = parkingOrderRepository.findById(parkingOrderId);
        if (parkingOrder.get() != null) {
            ParkingLot parkingLot = parkingOrder.get().getParkingLot();
            parkingLot.setCapacity(parkingLot.getCapacity() + 1);
            parkingOrder.get().setStatus("finished");
            parkingLotRepository.save(parkingLot);
            return parkingOrderRepository.save(parkingOrder.get());

        } else
            return null;
    }

}
