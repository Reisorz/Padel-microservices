package com.mls.service.auth.repository;

import com.mls.service.auth.model.InvalidTokenEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface InvalidTokenRepository extends JpaRepository<InvalidTokenEntity, String> {
    boolean existsByToken(String token);
    @Transactional
    @Modifying
    @Query("DELETE FROM InvalidTokenEntity t WHERE t.expirationDate < :currentDate")
    void deleteByExpirationDateBefore(@Param("currentDate") Date currentDate);
}
