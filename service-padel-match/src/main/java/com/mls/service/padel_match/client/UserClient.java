package com.mls.service.padel_match.client;

import com.mls.service.padel_match.dto.response.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "msvc-user")
public interface UserClient {

    @GetMapping("/user/get-user-by-id/{id}")
    public UserDTO getUserById(@PathVariable Long id);

    @GetMapping("/user/get-all-users-by-id")
    public List<UserDTO> getAllUsersByIds(@RequestParam List<Long> ids);

}
