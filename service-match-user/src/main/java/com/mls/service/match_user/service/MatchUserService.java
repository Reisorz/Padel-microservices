package com.mls.service.match_user.service;

import com.mls.service.match_user.model.MatchUserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchUserService {

    public MatchUserEntity addUserToMatch(Long userId, Long matchId, String team);

    public void removeUserFromMatch(Long userId, Long matchId);

    public List<Long> findAllUsersFromMatch(Long matchId);

    public List<Long> findAllMatchesFromUser(Long userId);
}
