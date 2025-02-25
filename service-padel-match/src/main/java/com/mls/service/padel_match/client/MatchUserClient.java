package com.mls.service.padel_match.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "msvc-match-user", url = "localhost:8085/match-user")
public interface MatchUserClient {

    @PostMapping("/add-user-to-match/{userId}/{matchId}/{team}/{isOrganizer}")
    public ResponseEntity<String> addUserToMatch(@PathVariable Long userId, @PathVariable Long matchId, @PathVariable String team, @PathVariable boolean isOrganizer);

}
