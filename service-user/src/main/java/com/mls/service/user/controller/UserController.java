package com.mls.service.user.controller;

import com.mls.service.user.dto.request.UserRegisterRequest;
import com.mls.service.user.dto.request.UserUpdateRequest;
import com.mls.service.user.model.UserEntity;
import com.mls.service.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register-user")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterRequest userRequest) {
        UserEntity user = UserEntity.builder()
                .city(userRequest.getCity())
                .email(userRequest.getEmail())
                .name(userRequest.getName())
                .padelLevel(userRequest.getPadelLevel())
                .password(userRequest.getPassword()) //encode later
                .build();
        userService.registerUser(user);
        return ResponseEntity.ok("User registered succesfully");
    }

    @GetMapping("/get-all-users")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/get-user-by-id/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id){
        UserEntity user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        userService.updateUser(id ,request);
        return ResponseEntity.ok("User updated succesfully");
    }
}
