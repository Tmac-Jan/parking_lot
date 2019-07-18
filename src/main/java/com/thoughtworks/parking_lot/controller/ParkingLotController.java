package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParingLotRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingLotController {

  @Autowired
  private ParingLotRepository paringLotRepository;

  @PostMapping(value = "/parking-lots")
  public ResponseEntity createParkingLot(@RequestBody ParkingLot parkingLot) {
    if (parkingLot.getId() == null) {
      return ResponseEntity.ok(paringLotRepository.save(parkingLot));
    } else {
      return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST);
    }
  }


  @DeleteMapping(value = "/parking-lots/{id}")
  public ResponseEntity deleteParkingLot(@PathVariable Integer id) {
    paringLotRepository.deleteById(id);
    return (ResponseEntity) ResponseEntity.status(HttpStatus.OK);

  }


}