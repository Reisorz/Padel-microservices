package com.mls.service.padel_match.service;

import com.mls.service.padel_match.dto.request.CreateMatchRequest;
import com.mls.service.padel_match.dto.response.PadelMatchDTO;
import com.mls.service.padel_match.model.PadelMatchEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface PadelMatchService {

    public List<PadelMatchEntity> getAllMatches();

    public PadelMatchEntity createPadelMatch(CreateMatchRequest request);

    public PadelMatchEntity getMatchById(Long id);

    public void deleteMatch(Long id);

    public List<PadelMatchDTO> getMatchesAndPlayersWithSpecifications(List<LocalDateTime> dates, Double userPadelLevel);


}
