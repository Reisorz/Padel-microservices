package com.mls.service.user.controller;

import com.mls.service.user.dto.request.UserRegisterRequest;
import com.mls.service.user.dto.request.UserUpdateRequest;
import com.mls.service.user.model.UserEntity;
import com.mls.service.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register-user")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody UserRegisterRequest userRequest) {
        userService.registerUser(userRequest);
        Map<String,String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        return ResponseEntity.ok(response);
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
    public ResponseEntity<Map<String ,String>> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        userService.updateUser(id ,request);
        Map<String,String> response = new HashMap<>();
        response.put("message", "User with id " + id + " updated successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<Map<String ,String>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        Map<String,String> response = new HashMap<>();
        response.put("message", "User with id " + id + " deleted successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all-users-by-id")
    public List<UserEntity> getAllUsersByIds(@RequestParam List<Long> ids) {
        return userService.getAllUsersByIds(ids);
    }


    @PostMapping("/upload-avatar-image")
    public ResponseEntity<String> uploadAvatarImage(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) {
        try {
            String url = userService.uploadAvatarImage(file, userId);
            return ResponseEntity.ok("Avatar image uploaded succesfully. URL: " + url);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error while uploading image: " + e.getMessage());
        }
    }

}


