package com.mls.service.padel_court;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServicePadelCourtApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicePadelCourtApplication.class, args);
	}

}
