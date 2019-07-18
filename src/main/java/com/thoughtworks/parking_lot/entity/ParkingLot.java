package com.thoughtworks.parking_lot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ParkingLot {

  @Id
  @GeneratedValue
  private Integer id;

  @Column(unique = true)
  private String name;

  private Integer capacity;

  private String location;

  public ParkingLot() {

  }

  public ParkingLot(String name, Integer capacity, String location) {
    this.name = name;
    this.capacity = capacity;
    this.location = location;
  }
  public ParkingLot(Integer id,String name, Integer capacity, String location) {
    this.id=id;
    this.name = name;
    this.capacity = capacity;
    this.location = location;
  }
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getCapacity() {
    return capacity;
  }

  public void setCapacity(Integer capacity) {
    this.capacity = capacity;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}
