package com.mls.service.user.service;

import org.springframework.data.domain.Pageable;

public interface FollowerService {

    public void follow(Long followerId, Long followedId);

}
