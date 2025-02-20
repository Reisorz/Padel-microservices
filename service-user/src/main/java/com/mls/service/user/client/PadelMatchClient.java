package com.mls.service.user.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "msvc-padel-match", url = "localhost:8082/padel-match")
public interface PadelMatchClient {
}
