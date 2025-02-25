package com.mls.service.match_user.service.impl;

import com.mls.service.match_user.model.MatchUserEntity;
import com.mls.service.match_user.repository.MatchUserRepository;
import com.mls.service.match_user.service.MatchUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchUserServiceImpl implements MatchUserService {

    @Autowired
    private MatchUserRepository matchUserRepository;

    @Override
    public MatchUserEntity addUserToMatch(Long userId, Long matchId, String team, boolean isOrganizer) {
        MatchUserEntity matchUser = MatchUserEntity.builder()
                .userId(userId)
                .matchId(matchId)
                .team(MatchUserEntity.Team.valueOf(team))
                .isOrganizer(isOrganizer)
                .build();
        return matchUserRepository.save(matchUser);
    }

    @Transactional
    @Override
    public void removeUserFromMatch(Long userId, Long matchId) {
        matchUserRepository.deleteByUserIdAndMatchId(userId, matchId);
    }

    @Override
    public List<MatchUserEntity> findAllUsersFromMatch(Long matchId) {
        return matchUserRepository.findAllUserIdsByMatchId(matchId);
    }

    @Override
    public List<MatchUserEntity> findAllMatchesFromUser(Long userId) {
        return  matchUserRepository.findAllMatchIdsByUserId(userId);
    }
}
