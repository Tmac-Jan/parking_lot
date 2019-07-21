package com.thoughtworks.parking_lot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.dto.ParkingOrderDTO;
import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.entity.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import com.thoughtworks.parking_lot.service.ParkingOrderSevice;
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
import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @MockBean
    private ParkingOrderSevice parkingOrderSevice;
    @Before public void setUp()throws Exception{
        ParkingLot parkingLot = new ParkingLot("gongbeiParkingLot",100,"gongbei");
        parkingLotRepository.save(parkingLot);

    }
    @Test
    public void should_return_parkingOrder_when_call_create_parkingOrder_API_and_id_is_null() throws Exception {
        ObjectMapper MAPPER = new ObjectMapper();
        ParkingOrder parkingOrder = mock(ParkingOrder.class);
        ParkingOrderDTO parkingOrderDTOActual = new ParkingOrderDTO("gongbeiParkingLot"
                ,"FN-001",new Date(),new Date(),"cteate",1);
        Mockito.when(
                parkingOrderSevice.createParkingOrder(
                        (ParkingOrderDTO)Mockito.any()
                )
        ).thenReturn(parkingOrder);
        mockMvc.perform(post("/parking-orders")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(MAPPER.writeValueAsString(parkingOrderDTOActual)
                ))
                .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id").value("1"));
    }
    @Test
    public void should_return_bad_request_status_code_when_call_create_parkingOrder_API_with_not_null_id() throws Exception {
        ObjectMapper MAPPER = new ObjectMapper();
        ParkingOrderDTO parkingOrderDTOActual = new ParkingOrderDTO(1,"gongbeiParkingLot"
                ,"FN-001",new Date(),new Date(),"cteate",1);
//        Mockito.when(
//              parkingOrderSevice.createParkingOrder(
//                      (ParkingOrderDTO)Mockito.any()
//              )
//        ).thenReturn(null);
        mockMvc.perform(post("/parking-orders")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(MAPPER.writeValueAsString(parkingOrderDTOActual)
                ))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void should_return_badRequest_status_when_call_finish_parkingOrder_API_with_wrong_id() throws Exception {
        Mockito.when(
                parkingOrderSevice.finishParkingOrder(
                        Mockito.anyInt()
                )
        ).thenReturn(null);
        mockMvc.perform(put("/parking-orders/{id}",100)
        ).andExpect(status().isBadRequest());
    }
    @Test
    public void should_return_error_message_status_code_when_call_create_parkingOrder_API_with_not_null_id() throws Exception {
        ObjectMapper MAPPER = new ObjectMapper();
        ParkingOrderDTO parkingOrderDTOActual = new ParkingOrderDTO(1,"gongbeiParkingLot"
                ,"FN-001",new Date(),new Date(),"cteate",1);
//        Mockito.when(
//              parkingOrderSevice.createParkingOrder(
//                      (ParkingOrderDTO)Mockito.any()
//              )
//        ).thenReturn(null);
        mockMvc.perform(post("/parking-orders")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(MAPPER.writeValueAsString(parkingOrderDTOActual)
                ))
                .andExpect(status().isOk())
                .andExpect(content().string("The parkingLot is full"));
    }
    @Test
    public void should_return_ok_status_when_call_finish_parkingOrder_API_with_true_id() throws Exception {
        ParkingOrder parkingOrder = mock(ParkingOrder.class);
        Mockito.when(
                parkingOrderSevice.finishParkingOrder(
                        Mockito.anyInt()
                )
        ).thenReturn(parkingOrder);
        mockMvc.perform(put("/parking-orders/{id}",100)
        ).andExpect(status().isOk());
    }
}