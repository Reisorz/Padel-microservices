package com.mls.service.match_user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-padel-match", url = "localhost:8080/padel-match")
public interface PadelMatchClient {

    @DeleteMapping("/delete-match/{id}")
    public ResponseEntity<String> deleteMatch(@PathVariable Long id);
}
