package com.mls.service.user.repository;

import com.mls.service.user.model.FollowerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepository extends JpaRepository<FollowerEntity, Long> {

    boolean existsByFollowerIdAndFollowedId(Long followerId, Long followedId);
}