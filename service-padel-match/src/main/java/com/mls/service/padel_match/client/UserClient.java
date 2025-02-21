package com.mls.service.padel_match.client;

import com.mls.service.padel_match.dto.response.UserDTO;
import com.mls.service.user.model.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "msvc-user", url = "localhost:8081/user")
public interface UserClient {

    @GetMapping("/get-user-by-id/{id}")
    public UserDTO getUserById(@PathVariable Long id);

    @PutMapping("/remove-match-from-user/{matchId}/{userId}")
    public ResponseEntity<String> removeMatchFromUser(@PathVariable Long matchId, @PathVariable Long userId);

    @PutMapping("/add-match-to-user/{matchId}/{userId}")
    public ResponseEntity<String> addMatchToUser(@PathVariable Long matchId, @PathVariable Long userId);
}
