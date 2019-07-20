package com.thoughtworks.parking_lot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.dto.ParkingOrderDTO;
import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.entity.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
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
import org.mockito.Mock;
import java.util.Date;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @title: ParkingOrderControllerTest
 * @projectName: parking_lot
 * @description: TODO
 * @author: macmanboy@foxmail.com(Gio Zhang)
 * @date: 2019/7/20 16:09
 */
@RunWith(SpringRunner.class)//测试引擎
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingOrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingOrderRepository parkingOrderRepository;

    @MockBean
    private ParkingLotRepository parkingLotRepository;

    @Test
    public void should_return_parkingOrder_when_call_create_parkingOrder_API() throws Exception {
        ObjectMapper MAPPER = new ObjectMapper();
        ParkingOrderDTO parkingOrderDTOActual = new ParkingOrderDTO("gongbeiParkingLot"
                ,"FN-001",new Date(),new Date(),"cteate",1);
        ParkingOrder ParkingOrderExpected = new ParkingOrder(1,
                "gongbeiParkingLot",
                "FN-001",
                new Date(),
                new Date(),
                "create",
                new ParkingLot(1,"gongbeiParkingLot",200,"gongbei")
        );
        Mockito.when(
                parkingOrderRepository.save(
                        Mockito.any()
                )
        ).thenReturn(ParkingOrderExpected);
        mockMvc.perform(post("/parking-orders")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(MAPPER.writeValueAsString(parkingOrderDTOActual)
                ))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }
    @Test
    public void should_return_bad_request_status_code_when_call_create_parkingOrder_API_and_the_parkingLot_capacity_smaller_than_1() throws Exception {
        ObjectMapper MAPPER = new ObjectMapper();
        ParkingOrderDTO parkingOrderDTOActual = new ParkingOrderDTO("gongbeiParkingLot"
                ,"FN-001",new Date(),new Date(),"cteate",1);

        Mockito.when(
              parkingLotRepository.findById(
                      Mockito.anyInt()
              )
        ).thenReturn(java.util.Optional.of(new ParkingLot(1,"gongbeiParkingLot",0,"gongbei")));
        mockMvc.perform(post("/parking-orders")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(MAPPER.writeValueAsString(parkingOrderDTOActual)
                ))
                .andExpect(status().isBadRequest());
    }
}