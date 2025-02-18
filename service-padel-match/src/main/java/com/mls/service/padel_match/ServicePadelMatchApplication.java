package com.mls.service.padel_match;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServicePadelMatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicePadelMatchApplication.class, args);
	}

}
