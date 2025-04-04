package com.mls.service.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableDiscoveryClient
@EnableScheduling
@SpringBootApplication
public class ServiceAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceAuthApplication.class, args);
	}

}
