package com.brikton.labapps.msfrontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsFrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsFrontendApplication.class, args);
	}

}
