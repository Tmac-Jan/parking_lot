package com.thoughtworks.parking_lot.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParingLotRepository;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)//测试引擎
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingLotControllerTest {

  @MockBean
  private ParingLotRepository paringLotRepository;

  @Autowired
  private MockMvc mockMvc;

  @Before
  public void setUp() {
    List<ParkingLot> parkingLots = new ArrayList<ParkingLot>() {{
      add(new ParkingLot("parkinglot1", 100, "tangjiawan"));
      add(new ParkingLot("parkinglot2", 100, "gongbei"));
      add(new ParkingLot("parkinglot3", 100, "jingding"));
    }};
    paringLotRepository.saveAll(parkingLots);
  }

  @Test
  public void should_return_parkingLot_when_call_create_parkingLot_api_with_correct_params()
      throws Exception {
    ParkingLot parkingLotResult = null;
    Mockito.when(
        paringLotRepository.save(
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
        paringLotRepository.save(
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
    } catch (Exception e) {
      System.out.println("result:" + e.getMessage());
      Assert.assertEquals("Request processing failed; "
          + "nested exception is java.lang.RuntimeException"
          + ": name is existing", e.getMessage());
    }

  }

  @Test
  public void should_return_HttpStatus_OK_when_call_delete_parkingLot_api_with_id()
      throws Exception {
    Mockito.doThrow(new RuntimeException("test success")).doNothing().when(paringLotRepository).deleteById(1);
    try {
      mockMvc.perform(delete("/parking-lots/{id}",1))
          .andReturn();
    }catch (Exception e){
      System.out.println("result:"+e.getMessage());
      Assert.assertEquals("Request processing failed; nested exception is java.lang.RuntimeException: test success",e.getMessage());
    }

  }
}