package com.mls.service.padel_match.client;

import com.mls.service.padel_match.dto.response.PadelCourtDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-padel-court", url = "localhost:8083/padel-court")
public interface PadelCourtClient {

    @GetMapping("/get-padel-court-by-id/{id}")
    public PadelCourtDTO getPadelCourtById(@PathVariable Long id);
}
