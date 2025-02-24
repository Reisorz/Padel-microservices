package com.mls.service.padel_match.controller;

import com.mls.service.padel_match.client.UserClient;
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

    @GetMapping("/get-match-by-id/{id}")
    public ResponseEntity<PadelMatchEntity> getMatchById(@PathVariable Long id) {
        PadelMatchEntity match = matchService.getMatchById(id);
        return ResponseEntity.ok(match);
    }

    @DeleteMapping("/delete-match/{id}")
    public ResponseEntity<String> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/delete-match-by-organizer/{id}")
    public ResponseEntity<String> deleteMatchByOrganizer(@PathVariable Long id) {
        matchService.deleteAllMatchesByOrganizer(id);
        return ResponseEntity.ok("All matches where organizer was " + id + " were eliminated");
    }


}
