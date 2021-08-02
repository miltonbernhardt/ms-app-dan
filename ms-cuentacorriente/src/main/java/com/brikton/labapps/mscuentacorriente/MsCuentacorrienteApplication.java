package com.brikton.labapps.mscuentacorriente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsCuentacorrienteApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsCuentacorrienteApplication.class, args);
    }

}
