package com.example.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("bookingModule", r -> r.path("/start/**")
                        .uri("lb://BOOKING")) 
                .route("invoiceModule", r -> r.path("/invoice/**")
                        .uri("lb://INVOICE"))
                .route("mailModule", r -> r.path("/mail/**")
                        .uri("lb://MAIL"))
                .route("ticketModule", r -> r.path("/ticket/**")
                        .uri("lb://TICKET"))
                .route("userModule", r -> r.path("/user/**")
                        .uri("lb://USER"))
                .build();
    }

}
