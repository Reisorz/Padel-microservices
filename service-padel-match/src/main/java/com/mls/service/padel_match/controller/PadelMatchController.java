package com.mls.service.padel_match.controller;

import com.mls.service.padel_match.client.UserClient;
import com.mls.service.padel_match.dto.request.CreateMatchRequest;
import com.mls.service.padel_match.dto.response.PadelMatchDTO;
import com.mls.service.padel_match.model.PadelMatchEntity;
import com.mls.service.padel_match.service.impl.PadelMatchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String,Long>> createMatch(@RequestBody CreateMatchRequest request) {
        PadelMatchEntity match = matchService.createPadelMatch(request);
        Map<String,Long> response = new HashMap<>();
        response.put("matchId", match.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-match-by-id/{id}")
    public ResponseEntity<PadelMatchEntity> getMatchById(@PathVariable Long id) {
        PadelMatchEntity match = matchService.getMatchById(id);
        return ResponseEntity.ok(match);
    }

    @DeleteMapping("/delete-match/{id}")
    public ResponseEntity<String> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.ok("Match with id " + id + " deleted successfully");
    }

    @GetMapping("/get-all-matches-and-players-with-specifications")
    public List<PadelMatchDTO> getMatchesAndPlayersWithSpecifications(
            @RequestParam(value = "dates", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            List<LocalDateTime> dates,

            @RequestParam(value = "userPadelLevel", required = false) Double userPadelLevel
    ) {
        return matchService.getMatchesAndPlayersWithSpecifications(dates, userPadelLevel);
    }

}
