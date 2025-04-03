package com.mls.service.auth.schedule;

import com.mls.service.auth.repository.InvalidTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenCleanupTask {
    @Autowired
    InvalidTokenRepository invalidTokenRepository;

    @Scheduled(fixedRate = 3600000) // Cada hora
    public void cleanupExpiredTokens() {
        System.out.println("cleanupExpiredTokens() starts");
        invalidTokenRepository.deleteByExpirationDateBefore(new Date());
    }
}
