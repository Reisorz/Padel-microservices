package com.mls.service.match_user.repository;

import com.mls.service.match_user.model.MatchUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchUserRepository extends JpaRepository<MatchUserEntity,Long> {

    public void deleteByUserIdAndMatchId(Long userId, Long matchId);

    @Query("SELECT mu.userId FROM MatchUserEntity mu WHERE mu.matchId = :matchId")
    List<Long> findAllUserIdsByMatchId(@Param("matchId") Long matchId);
}
