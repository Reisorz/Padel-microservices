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
    public ResponseEntity<PadelCourtEntity> savePadelCourt(@RequestBody PadelCourtSaveRequest request) {
        PadelCourtEntity court = padelCourtService.savePadelCourt(request);
        return ResponseEntity.ok(court);
    }

    @GetMapping("/get-all-padel-courts")
    public List<PadelCourtEntity> getAllPadelCourts(){
        return padelCourtService.getAllPadelCourts();
    }

    @GetMapping("/get-padel-court-by-id/{id}")
    public ResponseEntity<PadelCourtEntity> getPadelCourtById(@PathVariable Long id) {
        PadelCourtEntity court = padelCourtService.getCourtById(id);
        return ResponseEntity.ok(court);
    }
}
