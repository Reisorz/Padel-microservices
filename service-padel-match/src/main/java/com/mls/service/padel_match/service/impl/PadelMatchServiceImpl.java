package com.mls.service.padel_match.service.impl;

import com.mls.service.padel_match.client.MatchUserClient;
import com.mls.service.padel_match.client.PadelCourtClient;
import com.mls.service.padel_match.client.UserClient;
import com.mls.service.padel_match.dto.request.CreateMatchRequest;
import com.mls.service.padel_match.dto.response.MatchUserDTO;
import com.mls.service.padel_match.mapper.PadelMatchMapper;
import com.mls.service.padel_match.model.PadelMatchEntity;
import com.mls.service.padel_match.repository.PadelMatchRepository;
import com.mls.service.padel_match.service.PadelMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PadelMatchServiceImpl implements PadelMatchService {

    @Autowired
    private PadelMatchRepository matchRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private PadelCourtClient padelCourtClient;

    @Autowired
    private PadelMatchMapper mapper;

    @Autowired
    private MatchUserClient matchUserClient;

    @Override
    public List<PadelMatchEntity> getAllMatches() {
        return matchRepository.findAll();
    }

    @Override
    @Transactional
    public PadelMatchEntity createPadelMatch(CreateMatchRequest request) {
        //Check if court exists asking msvc-padel-court
        padelCourtClient.getPadelCourtById(request.getPadelCourtId());

        //Check if users exist
        List<Long> players = new ArrayList<>();
        players.addAll(request.getTeamA());
        players.addAll(request.getTeamB());
        for(Long player: players){
            userClient.getUserById(player);
        }

        PadelMatchEntity match = mapper.createMatchRequestToPadelMatchEntity(request);
        PadelMatchEntity savedMatch = matchRepository.save(match);

        boolean isOrganizer = false;
        List<Long> teamA = request.getTeamA();
        for(Long player: teamA){
            isOrganizer = Objects.equals(player, request.getOrganizer());
            matchUserClient.addUserToMatch(player,savedMatch.getId(),"A", isOrganizer);
        }

        List<Long> teamB = request.getTeamB();
        for(Long player: teamB){
            isOrganizer = Objects.equals(player, request.getOrganizer());
            matchUserClient.addUserToMatch(player,savedMatch.getId(),"B", isOrganizer);
        }

        return savedMatch;
    }

    @Override
    public PadelMatchEntity getMatchById(Long id) {
        return matchRepository.findById(id).orElseThrow(() -> new RuntimeException("Match with id " + id + " not found"));
    }

    @Override
    public void deleteMatch(Long matchId) {
        PadelMatchEntity match = matchRepository.findById(matchId).orElseThrow(() -> new RuntimeException("Match with id " + matchId + " not found"));

        List<MatchUserDTO> matchUsers = matchUserClient.getAllUsersFromMatch(matchId);
        for(MatchUserDTO player: matchUsers){
            matchUserClient.removeUserFromMatch(player.getUserId(),matchId);
        }

        matchRepository.deleteById(matchId);
    }



}
