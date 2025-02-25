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

        //Fix this*********************************
        return savedMatch;
    }

    @Override
    public PadelMatchEntity getMatchById(Long id) {
        return matchRepository.findById(id).orElseThrow(() -> new RuntimeException("Match with id " + id + " not found"));
    }

    @Override
    public void deleteMatch(Long matchId) {
        PadelMatchEntity match = matchRepository.findById(matchId).orElseThrow(() -> new RuntimeException("Match with id " + matchId + " not found"));
        //Fix this*********************

        matchRepository.deleteById(matchId);
    }



}
