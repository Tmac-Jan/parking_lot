package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class ParingLoyRepositoryTest {

  @Autowired
  private ParingLoyRepository paringLoyRepository;

  @Before
  public void setUp(){

  }

//  @Test
//  public void should_return_parkingLot_when_call_save_with_correct_params(){
//
//  }
}