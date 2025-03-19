package com.mls.service.padel_court.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mls.service.padel_court.DataProvider;
import com.mls.service.padel_court.dto.request.PadelCourtSaveRequest;
import com.mls.service.padel_court.model.PadelCourtEntity;
import com.mls.service.padel_court.service.impl.PadelCourtServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PadelCourtController.class)
public class PadelCourtControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PadelCourtServiceImpl service;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testSavePadelCourt_ShouldSavePadelCourt() throws Exception {

        PadelCourtSaveRequest request = DataProvider.newPadelCourtSaveRequest();
        PadelCourtEntity savedEntity = DataProvider.savePadelCourtEntity();
        given(service.savePadelCourt(any(PadelCourtSaveRequest.class)))
                .willReturn(savedEntity);


        ResultActions response = mockMvc.perform(post("/padel-court/save-padel-court")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)));


        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Padel court saved"));
    }

    @Test
    void testGetAllPadelCourtst_ShouldReturnListOfPadelCourts() throws Exception {
        List<PadelCourtEntity> courts = DataProvider.listPadelCourtEntity();
        given(service.getAllPadelCourts()).willReturn(courts);

        ResultActions response = mockMvc.perform(get("/padel-court/get-all-padel-courts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(courts)));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(courts.size()))
                .andExpect(jsonPath("$[0].name").value(courts.get(0).getName()));
    }

    @Test
    void testGetPadelCourtById_ShouldReturnPadelCourt() throws Exception {
        Long id = 1L;
        PadelCourtEntity court = DataProvider.getPadelCourtEntity();
        given(service.getCourtById(anyLong())).willReturn(court);

        ResultActions response = mockMvc.perform(get("/padel-court/get-padel-court-by-id/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(court)));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(court.getName()))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.address").value(court.getAddress()))
                .andExpect(jsonPath("$.number").value(court.getNumber()))
                .andExpect(jsonPath("$.glass").value(court.isGlass()))
                .andExpect(jsonPath("$.exterior").value(court.isExterior()));
    }
}
