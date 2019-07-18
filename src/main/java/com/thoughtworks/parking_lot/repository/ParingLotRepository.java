package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParingLotRepository extends JpaRepository<ParkingLot,Integer> {

//  Page<ParkingLot> findAll(Pageable pageable);
}
