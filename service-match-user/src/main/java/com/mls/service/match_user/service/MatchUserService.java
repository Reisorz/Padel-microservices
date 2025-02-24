package com.mls.service.match_user.service;

import com.mls.service.match_user.model.MatchUserEntity;
import org.springframework.stereotype.Service;

@Service
public interface MatchUserService {

    public MatchUserEntity addUserToMatch(Long userId, Long matchId, String team);

    public void removeUserFromMatch(Long userId, Long matchId);
}
