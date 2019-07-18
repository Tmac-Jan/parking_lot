package com.thoughtworks.parking_lot.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParingLoyRepository;
import java.util.ArrayList;
import java.util.List;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)//测试引擎
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingLotControllerTest {

  @MockBean
  private ParingLoyRepository paringLoyRepository;

  @Autowired
  private MockMvc mockMvc;

  @Before
  public void setUp() {
    List<ParkingLot> parkingLots = new ArrayList<ParkingLot>() {{
      add(new ParkingLot("parkinglot1", 100, "tangjiawan"));
      add(new ParkingLot("parkinglot2", 100, "gongbei"));
      add(new ParkingLot("parkinglot3", 100, "jingding"));
    }};
    paringLoyRepository.saveAll(parkingLots);
  }

  @Test
  public void should_return_parkingLot_when_call_create_parkingLot_api_with_correct_params()
      throws Exception {
    ParkingLot parkingLotResult = null;
    Mockito.when(
        paringLoyRepository.save(
            Mockito.any()
        )
    ).thenReturn(new ParkingLot(1, "p1", 2000, "zhuhai"));
    MvcResult result = mockMvc.perform(post("/parking-lots")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content("{\n"
            + "\t\"name\":\"p1\",\n"
            + "\t\"capacity\":2000,\n"
            + "\t\"location\":\"zhuhai\"\n"
            + "}")
    )
        .andReturn();
    Assert.assertEquals("{\"id\":1,\"name\":\"p1\",\"capacity\":2000,\"location\":\"zhuhai\"}",
        (result.getResponse().getContentAsString()));
    System.out.println(result.getResponse().getContentAsString());
  }

  @Test
  public void should_return_parkingLot_when_call_create_parkingLot_api_with_existing_parkingLot_name()
      throws Exception {
    ParkingLot parkingLotResult = null;
    Mockito.when(
        paringLoyRepository.save(
            Mockito.any()
        )
    ).thenThrow(new RuntimeException("name is existing"));
    try {
     mockMvc.perform(post("/parking-lots")
          .contentType(MediaType.APPLICATION_JSON_UTF8)
          .content("{\n"
              + "\t\"name\":\"p1\",\n"
              + "\t\"capacity\":2000,\n"
              + "\t\"location\":\"zhuhai\"\n"
              + "}")
      ).andReturn();
    }catch (Exception e){
      System.out.println("result:"+e.getMessage());
      Assert.assertEquals("Request processing failed; "
          + "nested exception is java.lang.RuntimeException"
          + ": name is existing",e.getMessage());
    }

  }
}