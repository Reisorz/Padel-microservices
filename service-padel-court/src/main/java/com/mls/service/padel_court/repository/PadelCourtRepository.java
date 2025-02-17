package com.mls.service.padel_court.repository;

import com.mls.service.padel_court.model.PadelCourtEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PadelCourtRepository extends JpaRepository<PadelCourtEntity, Long> {
}
