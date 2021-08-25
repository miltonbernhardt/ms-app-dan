package com.brikton.labapps.msproducto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
public class MsProductoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsProductoApplication.class, args);
    }

}
