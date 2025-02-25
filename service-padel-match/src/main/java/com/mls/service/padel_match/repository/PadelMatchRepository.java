package com.mls.service.padel_match.repository;

import com.mls.service.padel_match.model.PadelMatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PadelMatchRepository extends JpaRepository<PadelMatchEntity, Long> {

}
