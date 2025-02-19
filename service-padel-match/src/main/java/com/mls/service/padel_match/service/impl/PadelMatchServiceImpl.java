package com.mls.service.padel_match.service.impl;

import com.mls.service.padel_match.dto.request.CreateMatchRequest;
import com.mls.service.padel_match.mapper.PadelMatchMapper;
import com.mls.service.padel_match.model.PadelMatchEntity;
import com.mls.service.padel_match.repository.PadelMatchRepository;
import com.mls.service.padel_match.service.PadelMatchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PadelMatchServiceImpl implements PadelMatchService {

    @Autowired
    private PadelMatchRepository matchRepository;

    @Autowired
    private PadelMatchMapper mapper;

    @Override
    public List<PadelMatchEntity> getAllMatches() {
        return matchRepository.findAll();
    }

    @Override
    @Transactional
    public PadelMatchEntity createPadelMatch(CreateMatchRequest request) {
        //Check if organizer exist
        PadelMatchEntity match = mapper.createMatchRequestToPadelMatchEntity(request);
        return matchRepository.save(match);
    }
}
