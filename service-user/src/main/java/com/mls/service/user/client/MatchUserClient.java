package com.mls.service.user.client;


import com.mls.service.user.dto.response.MatchUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-match-user", url = "localhost:8080/match-user")
public interface MatchUserClient {

    @DeleteMapping("/remove-user-from-match/{userId}/{matchId}")
    public ResponseEntity<String> removeUserFromMatch(@PathVariable Long userId, @PathVariable Long matchId);

    @GetMapping("/get-all-matches-from-user/{userId}")
    public List<MatchUserDTO> getAllMatchesFromUser(@PathVariable Long userId);
}
