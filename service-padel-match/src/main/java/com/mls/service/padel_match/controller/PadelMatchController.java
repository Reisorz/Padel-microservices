package com.mls.service.padel_match.controller;

import com.mls.service.padel_match.dto.request.CreateMatchRequest;
import com.mls.service.padel_match.model.PadelMatchEntity;
import com.mls.service.padel_match.service.impl.PadelMatchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/padel-match")
public class PadelMatchController {

    @Autowired
    private PadelMatchServiceImpl matchService;

    @GetMapping("/get-all-matches")
    public List<PadelMatchEntity> getAllMatches(){
        return matchService.getAllMatches();
    }

    @PostMapping("/create-match")
    public ResponseEntity<String> createMatch(@RequestBody CreateMatchRequest request) {
        matchService.createPadelMatch(request);
        return ResponseEntity.ok("Match created");
    }
}
