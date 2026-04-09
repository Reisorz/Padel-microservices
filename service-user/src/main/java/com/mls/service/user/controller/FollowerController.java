package com.mls.service.user.controller;

import com.mls.service.user.dto.request.UserRegisterRequest;
import com.mls.service.user.dto.response.UserDTO;
import com.mls.service.user.service.FollowerService;
import com.mls.service.user.service.impl.FollowerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/follower")
public class FollowerController {

    private final FollowerServiceImpl followerService;

    public FollowerController(FollowerServiceImpl followerService) {
        this.followerService = followerService;
    }

    @PostMapping("/follow-user/{followerId}/{followedId}")
    public ResponseEntity<Map<String, String>> followUser(@PathVariable Long followerId, @PathVariable Long followedId) {
        followerService.follow(followerId, followedId);
        Map<String,String> response = new HashMap<>();
        response.put("message", "User has been followed successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/followers/{userId}")
    public ResponseEntity<Page<UserDTO>> getFollowersByUserId(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(followerService.getFollowersByUserId(userId, page, size));
    }
}
