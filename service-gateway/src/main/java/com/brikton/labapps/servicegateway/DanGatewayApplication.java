package com.brikton.labapps.servicegateway;

import com.brikton.labapps.servicegateway.filter.ErrorFilter;
import com.brikton.labapps.servicegateway.filter.PostFilter;
import com.brikton.labapps.servicegateway.filter.RouteFilter;
import com.brikton.labapps.servicegateway.filters.PreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class DanGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DanGatewayApplication.class, args);
    }

    @Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }

    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }

    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }

    @Bean
    public RouteFilter routeFilter() {
        return new RouteFilter();
    }
}