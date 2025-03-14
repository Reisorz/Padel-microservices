package com.mls.service.padel_court.controller;


import com.mls.service.padel_court.DataProvider;
import com.mls.service.padel_court.dto.request.PadelCourtSaveRequest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PadelCourtControllerTestRestTemplate {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String baseUrl = "http://localhost:8080/padel-court/";

    @Test
    @Order(1)
    void savePadelCourt_ShouldSaveNewPadelCourt(){
        PadelCourtSaveRequest request = DataProvider.newPadelCourtSaveRequest();

        //NEED A VALID TOKEN FOR JWT FILTER, FIX THIS
        ResponseEntity<String> response = testRestTemplate.postForEntity(baseUrl + "save-padel-court",request, String.class);

        assertEquals(HttpStatus.OK,response.getStatusCode());

    }


}
