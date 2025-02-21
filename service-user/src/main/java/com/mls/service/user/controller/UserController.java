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
        userService.registerUser(userRequest);
        return ResponseEntity.ok("User registered successfully");
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
        return ResponseEntity.ok("User with id " + id + " updated successfully");
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User with id " + id + " deleted successfully");
    }

    @PutMapping("/remove-match-from-user/{matchId}/{userId}")
    public ResponseEntity<String> removeMatchFromUser(@PathVariable Long matchId, @PathVariable Long userId) {
        userService.removeMatchFromUser(matchId,userId);
        return ResponseEntity.ok("Match with id " + matchId + " successfully removed from user with id " + userId);
    }
}
