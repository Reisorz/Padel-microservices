package com.mls.service.match_user.controller;

import com.mls.service.match_user.model.MatchUserEntity;
import com.mls.service.match_user.service.MatchUserService;
import com.mls.service.match_user.service.impl.MatchUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match-user")
public class MatchUserController {

    @Autowired
    private MatchUserServiceImpl matchUserService;

    @PostMapping("/add-user-to-match")
    public ResponseEntity<String> addUserToMatch(@RequestBody MatchUserEntity matchUser) {
        matchUserService.addUserToMatch(matchUser);
        return ResponseEntity.ok("User " + matchUser.getId() + " has been added to the match " + matchUser.getMatchId());
    }

    @DeleteMapping("/remove-user-from-match/{userId}/{matchId}")
    public ResponseEntity<String> removeUserFromMatch(@PathVariable Long userId, @PathVariable Long matchId) {
        matchUserService.removeUserFromMatch(userId, matchId);
        return ResponseEntity.ok("User " + userId + " has been removed from the match " + matchId);
    }

    @DeleteMapping("/delete-all-users-from-match/{matchId}")
    public ResponseEntity<String > deleteAllUsersFromMatch(@PathVariable Long matchId){
        matchUserService.deleteAllUsersFromMatch(matchId);
        return ResponseEntity.ok("All users from match " + matchId + " were eliminated");
    }

    @GetMapping("/get-all-users-from-match/{matchId}")
    public ResponseEntity<List<MatchUserEntity>> getAllUsersFromMatch(@PathVariable Long matchId) {
        List<MatchUserEntity> users = matchUserService.findAllUsersFromMatch(matchId);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/get-all-matches-from-user/{userId}")
    public ResponseEntity<List<MatchUserEntity>> getAllMatchesFromUser(@PathVariable Long userId) {
        List<MatchUserEntity> matches = matchUserService.findAllMatchesFromUser(userId);
        return ResponseEntity.ok(matches);
    }

}
