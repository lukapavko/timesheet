package com.gateway.api_gateway.configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.Duration;
@CrossOrigin
@Configuration
public class GatewayRouting {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route( myProfileRoute ->myProfileRoute
                        .path("/myProfile/**")
                        .filters(pathFilter ->pathFilter.circuitBreaker(consumer ->consumer
                                .setName("my_profile_path_filter")
                                .setFallbackUri("/defaultFallbackUrl"))
                        )
                        .uri("lb://MY-PROFILE-MICROSERVICE")
                )
                .route(  timesheetMicroservice ->timesheetMicroservice  // support for timesheet microservice routing
                        .path("/task/**")
                        .filters(pathFilter ->pathFilter.circuitBreaker(consumer ->consumer
                                .setName("timesheet_path_filter")
                                .setFallbackUri("/defaultFallbackUrl"))
                        )
                        .uri("lb://TIMESHEET-MICROSERVICE")
                )
                .build();
    }


}
