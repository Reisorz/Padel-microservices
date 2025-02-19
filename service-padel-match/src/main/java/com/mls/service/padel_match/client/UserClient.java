package com.mls.service.padel_match.client;

import com.mls.service.padel_match.dto.response.UserDTO;
import com.mls.service.user.model.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-user", url = "localhost:8081/user")
public interface UserClient {

    @GetMapping("/get-user-by-id/{id}")
    public UserDTO getUserById(@PathVariable Long id);
}
