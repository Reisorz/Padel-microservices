package com.mls.service.match_user.service.impl;

import com.mls.service.match_user.model.MatchUserEntity;
import com.mls.service.match_user.repository.MatchUserRepository;
import com.mls.service.match_user.service.MatchUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchUserServiceImpl implements MatchUserService {

    @Autowired
    private MatchUserRepository matchUserRepository;

    @Override
    public MatchUserEntity addUserToMatch(Long userId, Long matchId, String team) {
        MatchUserEntity matchUser = MatchUserEntity.builder()
                .userId(userId)
                .matchId(matchId)
                .team(MatchUserEntity.Team.valueOf(team))
                .build();
        return matchUserRepository.save(matchUser);
    }

    @Override
    public void removeUserFromMatch(Long userId, Long matchId) {
        matchUserRepository.deleteByUserIdAndMatchId(userId, matchId);
    }
}
