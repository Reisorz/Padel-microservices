package com.mls.service.padel_court.service;

import com.mls.service.padel_court.dto.request.PadelCourtSaveRequest;
import com.mls.service.padel_court.model.PadelCourtEntity;
import com.mls.service.padel_court.repository.PadelCourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PadelCourtService {

    public PadelCourtEntity savePadelCourt(PadelCourtSaveRequest request);

    public List<PadelCourtEntity> getAllPadelCourts();


}
