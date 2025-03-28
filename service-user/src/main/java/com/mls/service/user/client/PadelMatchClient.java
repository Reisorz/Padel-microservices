package com.mls.service.user.client;

import com.mls.service.user.dto.response.PadelMatchDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-padel-match")
public interface PadelMatchClient {

    @GetMapping("/padel-match/get-match-by-id/{id}")
    public PadelMatchDTO getMatchById(@PathVariable Long id);


    @DeleteMapping("/padel-match/delete-match/{id}")
    public String deleteMatch(@PathVariable Long id);
}
