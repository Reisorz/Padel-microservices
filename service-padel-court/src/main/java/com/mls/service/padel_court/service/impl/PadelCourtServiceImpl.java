package com.mls.service.padel_court.service.impl;

import com.mls.service.padel_court.dto.request.PadelCourtSaveRequest;
import com.mls.service.padel_court.model.PadelCourtEntity;
import com.mls.service.padel_court.repository.PadelCourtRepository;
import com.mls.service.padel_court.service.PadelCourtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PadelCourtServiceImpl implements PadelCourtService {

    @Autowired
    private PadelCourtRepository padelCourtRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public PadelCourtEntity savePadelCourt(PadelCourtSaveRequest request) {
        PadelCourtEntity padelCourt = mapper.map(request, PadelCourtEntity.class);
        return padelCourtRepository.save(padelCourt);
    }

    @Override
    public List<PadelCourtEntity> getAllPadelCourts() {
        return padelCourtRepository.findAll();
    }
}
