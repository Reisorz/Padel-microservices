package com.mls.service.user.client;

import com.mls.service.user.dto.request.AuthUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-auth", url = "localhost:8080/auth")
public interface AuthUserClient {

    @PostMapping("/register")
    public void register (@RequestBody AuthUserDTO authUserDTO);
}
