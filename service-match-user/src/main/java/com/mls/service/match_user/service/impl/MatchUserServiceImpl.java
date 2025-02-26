package com.mls.service.match_user.service.impl;

import com.mls.service.match_user.client.PadelMatchClient;
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

    @Autowired
    private PadelMatchClient padelMatchClient;

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
        MatchUserEntity matchUser = matchUserRepository.findByMatchIdAndUserId(matchId,userId);
        boolean isOrganizer = matchUser.isOrganizer();

        if(isOrganizer) {
            padelMatchClient.deleteMatch(matchId);
        }
        else {
            matchUserRepository.deleteByUserIdAndMatchId(userId, matchId);
        }
    }

    @Transactional
    @Override
    public void deleteAllUsersFromMatch(Long matchId) {
        matchUserRepository.deleteByMatchId(matchId);
    }

    @Override
    public List<MatchUserEntity> findAllUsersFromMatch(Long matchId) {
        return matchUserRepository.findAllUsersByMatchId(matchId);
    }

    @Override
    public List<MatchUserEntity> findAllMatchesFromUser(Long userId) {
        return  matchUserRepository.findAllMatchIdsByUserId(userId);
    }
}
