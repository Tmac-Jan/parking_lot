package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.dto.ParkingOrderDTO;
import com.thoughtworks.parking_lot.entity.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import com.thoughtworks.parking_lot.service.ParkingOrderSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @title: ParkingOrderController
 * @projectName: parking_lot
 * @description: TODO
 * @author: macmanboy@foxmail.com(Gio Zhang)
 * @date: 2019/7/20 15:59
 */
@RestController
public class ParkingOrderController {
    @Autowired
    private ParkingOrderRepository parkingOrderRepository;
    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Autowired
    private ParkingOrderSevice parkingOrderSevice;
    @PostMapping(value = "/parking-orders")
    public ResponseEntity createParkingOrder(@RequestBody ParkingOrderDTO parkingOrderDTO){
     if (parkingOrderDTO.getId()==null){
         ParkingOrder parkingOrder = parkingOrderSevice.createParkingOrder(parkingOrderDTO);
         if (parkingOrder!=null){
             return  ResponseEntity.ok(parkingOrder);
         }else{
             return ResponseEntity.ok("The parkingLot is full");
         }
     }else{
         return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST);
     }
    }
    @PutMapping(value = "/parking-orders/{id}")
    public ResponseEntity finishParkingOrder(@PathVariable Integer id){
               ParkingOrder parkingOrder = parkingOrderSevice.finishParkingOrder(id);
               if (parkingOrder!=null){
                   return (ResponseEntity) ResponseEntity.status(HttpStatus.OK);
               }else

                   return (ResponseEntity) ResponseEntity.badRequest();

    }
}
