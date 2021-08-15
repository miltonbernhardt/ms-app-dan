package com.brikton.labapps.msliquidacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsLiquidacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsLiquidacionApplication.class, args);
	}

}
