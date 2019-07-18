package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParingLoyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingLotController {

  @Autowired
  private ParingLoyRepository paringLoyRepository;

  @PostMapping(value = "/parking-lots")
  public ResponseEntity createParkingLot(@RequestBody ParkingLot parkingLot) {
    if (parkingLot.getId() == null) {
      return ResponseEntity.ok(paringLoyRepository.save(parkingLot));
    } else {
      return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST);
    }
  }
}
