package com.mls.service.match_user.service;

import com.mls.service.match_user.model.MatchUserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchUserService {

    public MatchUserEntity addUserToMatch(Long userId, Long matchId, String team, boolean isOrganizer);

    public void removeUserFromMatch(Long userId, Long matchId);

    public List<MatchUserEntity> findAllUsersFromMatch(Long matchId);

    public List<MatchUserEntity> findAllMatchesFromUser(Long userId);
}
