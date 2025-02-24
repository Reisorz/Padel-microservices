package com.mls.service.match_user.repository;

import com.mls.service.match_user.model.MatchUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchUserRepository extends JpaRepository<MatchUserEntity,Long> {

    public void deleteByUserIdAndMatchId(Long userId, Long matchId);
}
