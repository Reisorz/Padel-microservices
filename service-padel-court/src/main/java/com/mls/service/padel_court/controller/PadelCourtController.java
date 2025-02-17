package com.mls.service.padel_court.controller;

import com.mls.service.padel_court.dto.request.PadelCourtSaveRequest;
import com.mls.service.padel_court.model.PadelCourtEntity;
import com.mls.service.padel_court.service.impl.PadelCourtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/padel-court")
public class PadelCourtController {

    @Autowired
    private PadelCourtServiceImpl padelCourtService;

    @PostMapping("/save-padel-court")
    public ResponseEntity<String> savePadelCourt(@RequestBody PadelCourtSaveRequest request) {
        padelCourtService.savePadelCourt(request);
        return ResponseEntity.ok("Padel court saved");
    }

    @GetMapping("/get-all-padel-courts")
    public List<PadelCourtEntity> getAllPadelCourts(){
        return padelCourtService.getAllPadelCourts();
    }
}
