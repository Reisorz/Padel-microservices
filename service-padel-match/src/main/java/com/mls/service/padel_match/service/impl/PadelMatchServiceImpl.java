package com.mls.service.padel_match.service.impl;

import com.mls.service.padel_match.client.PadelCourtClient;
import com.mls.service.padel_match.client.UserClient;
import com.mls.service.padel_match.dto.request.CreateMatchRequest;
import com.mls.service.padel_match.mapper.PadelMatchMapper;
import com.mls.service.padel_match.model.PadelMatchEntity;
import com.mls.service.padel_match.repository.PadelMatchRepository;
import com.mls.service.padel_match.service.PadelMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<PadelMatchEntity> getAllMatches() {
        return matchRepository.findAll();
    }

    @Override
    @Transactional
    public PadelMatchEntity createPadelMatch(CreateMatchRequest request) {
        //Check if court exists asking msvc-padel-court
        padelCourtClient.getPadelCourtById(request.getPadelCourtId());

        PadelMatchEntity match = mapper.createMatchRequestToPadelMatchEntity(request);
        PadelMatchEntity savedMatch = matchRepository.save(match);

        List<Long> users = new ArrayList<>();
        users.addAll(savedMatch.getTeamA());
        users.addAll(savedMatch.getTeamB());

        //Checks if users exist and adds the match to them by UserClient
        for (Long user: users){
            userClient.addMatchToUser(savedMatch.getId(),user);
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

        List<Long> users = new ArrayList<>();
        users.addAll(match.getTeamA());
        users.addAll(match.getTeamB());

        if(!users.isEmpty()){
            for(Long user: users){
                userClient.removeMatchFromUser(matchId, user);
            }
        }

        matchRepository.deleteById(matchId);
    }

    @Override
    public void deleteAllMatchesByOrganizer(Long id) {
        List<PadelMatchEntity> matches = matchRepository.findAllByOrganizer(id);

        for(PadelMatchEntity match : matches) {
            deleteMatch(match.getId());
        }
    }

    @Override
    public void removeUserFromMatch(Long userId, Long matchId) {
        PadelMatchEntity match = matchRepository.findById(matchId).orElseThrow(() -> new RuntimeException("Match with id " + matchId + " not found"));
        match.getTeamA().remove(userId);
        match.getTeamB().remove(userId);
        matchRepository.save(match);
    }

}
