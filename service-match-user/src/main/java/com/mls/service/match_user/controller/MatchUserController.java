package com.mls.service.match_user.controller;

import com.mls.service.match_user.service.MatchUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match-user")
public class MatchUserController {

    @Autowired
    private MatchUserService matchUserService;

    @PostMapping("/add-user-to-match/{userId}/{matchId}/{team}/{isOrganizer}")
    public ResponseEntity<String> addUserToMatch(@PathVariable Long userId, @PathVariable Long matchId, @PathVariable String team, @PathVariable boolean isOrganizer) {
        matchUserService.addUserToMatch(userId, matchId, team, isOrganizer);
        return ResponseEntity.ok("User " + userId + " has been added to the match " + matchId);
    }

    @DeleteMapping("/remove-user-from-match/{userId}/{matchId}")
    public ResponseEntity<String> removeUserFromMatch(@PathVariable Long userId, @PathVariable Long matchId) {
        matchUserService.removeUserFromMatch(userId, matchId);
        return ResponseEntity.ok("User " + userId + " has been removed from the match " + matchId);
    }

    @GetMapping("/get-all-users-from-match/{matchId}")
    public ResponseEntity<List<Long>> getAllUsersFromMatch(@PathVariable Long matchId) {
        List<Long> userIds = matchUserService.findAllUsersFromMatch(matchId);
        return ResponseEntity.ok(userIds);
    }

    @GetMapping("/get-all-matches-from-user/{userId}")
    public ResponseEntity<List<Long>> getAllMatchesFromUser(@PathVariable Long userId) {
        List<Long> matchIds = matchUserService.findAllMatchesFromUser(userId);
        return ResponseEntity.ok(matchIds);
    }

}
