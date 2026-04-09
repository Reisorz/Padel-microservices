package com.mls.service.user.service;

import com.mls.service.user.dto.response.UserDTO;
import com.mls.service.user.model.FollowerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FollowerService {

    public void follow(Long followerId, Long followedId);

    public Page<UserDTO> getFollowersByUserId(Long userId, int page, int size);

}
