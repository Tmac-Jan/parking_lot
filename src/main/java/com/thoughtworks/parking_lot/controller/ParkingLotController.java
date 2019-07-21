package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParkingLotController {

  @Autowired
  private ParkingLotRepository parkingLotRepository;

  @PostMapping(value = "/parking-lots")
  public ResponseEntity createParkingLot(@RequestBody ParkingLot parkingLot) {
    if (parkingLot.getId() == null) {
      return ResponseEntity.ok(parkingLotRepository.save(parkingLot));
    } else {
      return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping(value = "/parking-lots/{id}")
  public ResponseEntity deleteParkingLot(@PathVariable Integer id) {
    parkingLotRepository.deleteById(id);
    return (ResponseEntity) ResponseEntity.status(HttpStatus.OK);

  }

  @RequestMapping(value = "/parking-lots", method = RequestMethod.GET)
  public ResponseEntity<List<ParkingLot>> getAllParkingLotByPageAndPageSize(
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "15") Integer pageSize) {
    if (page > 0 && pageSize > 0) {
      Page<ParkingLot> pageContent = parkingLotRepository.findAll(PageRequest
          .of(page - 1, pageSize));
      return ResponseEntity.ok(pageContent.getContent());
    }
    return ResponseEntity.ok(parkingLotRepository.findAll());
  }

  @RequestMapping(value = "/parking-lots/{id}", method = RequestMethod.GET)
  public ResponseEntity getParkingLotById(
      @PathVariable Integer id) {
    return ResponseEntity.ok(parkingLotRepository.findById(id));
  }

  @PutMapping(value = "/parking-lots/{id}")
  public ResponseEntity modifyParkingLot(@PathVariable Integer id,
      @RequestBody ParkingLot parkingLot) {
    if (id != null) {
      return ResponseEntity.ok(parkingLotRepository.save(parkingLot));
    } else {
      return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST);
    }
  }
}
