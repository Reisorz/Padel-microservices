package com.mls.service.user.controller;

import com.mls.service.user.dto.request.UserRegisterRequest;
import com.mls.service.user.service.FollowerService;
import com.mls.service.user.service.impl.FollowerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
}
