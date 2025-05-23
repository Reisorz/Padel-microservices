package com.mls.service.padel_match.client;


import com.mls.service.padel_match.dto.response.MatchUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "msvc-match-user")
public interface MatchUserClient {

    @PostMapping("/match-user/add-user-to-match/{userId}/{matchId}/{team}/{isOrganizer}")
    public ResponseEntity<String> addUserToMatch(@PathVariable Long userId, @PathVariable Long matchId, @PathVariable String team, @PathVariable boolean isOrganizer);

    @GetMapping("/match-user/get-all-users-from-match/{matchId}")
    public List<MatchUserDTO> getAllUsersFromMatch(@PathVariable Long matchId);

    @DeleteMapping("/match-user/remove-user-from-match/{userId}/{matchId}")
    public ResponseEntity<String> removeUserFromMatch(@PathVariable Long userId, @PathVariable Long matchId);

    @DeleteMapping("/match-user/delete-all-users-from-match/{matchId}")
    public ResponseEntity<String > deleteAllUsersFromMatch(@PathVariable Long matchId);
}
