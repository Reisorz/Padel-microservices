package com.mls.service.auth.controller;

import com.mls.service.auth.dto.AuthUserDTO;
import com.mls.service.auth.dto.TokenDTO;
import com.mls.service.auth.model.AuthUserEntity;
import com.mls.service.auth.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Autowired
    AuthUserService authUserService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody AuthUserDTO authUserDTO){
        TokenDTO token = authUserService.login(authUserDTO);
        if(token == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(token);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDTO> validate(@RequestParam String token){
        TokenDTO tokenDTO = authUserService.validate(token);
        if(tokenDTO == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tokenDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthUserEntity> register (@RequestBody AuthUserDTO authUserDTO) {
        AuthUserEntity authUser = authUserService.register(authUserDTO);
        if(authUser == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(authUser);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String ,String>> logout(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        authUserService.logout(token);
        Map<String ,String> response = new HashMap<>();
        response.put("Server response: ", "Token revoked");
        return ResponseEntity.ok(response);
    }
}
