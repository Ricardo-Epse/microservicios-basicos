package com.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class GatewayConfig {

	@Bean
	@Profile("localhostRouter-noEureka")
	public RouteLocator configLocalNoEureka(RouteLocatorBuilder builder) {
		return builder.routes().route(r -> r.path("/api/v1/dragonball/*").uri("http://localhost:8082"))
				.route(r -> r.path("/api/v1/rickandmorty/*").uri("http://localhost:8083")).build();
	}

	@Bean
	@Profile("localhostRouter-Eureka")
	public RouteLocator configLocalEureka(RouteLocatorBuilder builder) {
		return builder.routes().route(r -> r.path("/api/v1/dragonball/*").uri("lb://PRUEBAS-DRAGON-BALL"))
				.route(r -> r.path("/api/v1/rickandmorty/*").uri("lb://PRUEBAS-RICK-AND-MORTY")).build();
	}

	@Bean
	@Profile("localhostRouter-Eureka-cb")
	public RouteLocator configLocalEurekaCb(RouteLocatorBuilder builder) {
		return builder.routes().route(r -> r.path("/api/v1/dragonball/*")
					.filters(f->f.circuitBreaker(c->c.setName("failoverCB")
							.setFallbackUri("forward:/api/v1/db-failover/dragonball/characters")
							.setRouteId("dbFailover")))
					.uri("lb://PRUEBAS-DRAGON-BALL"))
				.route(r -> r.path("/api/v1/rickandmorty/*").
						uri("lb://PRUEBAS-RICK-AND-MORTY"))
				.route(r -> r.path("/api/v1/db-failover/dragonball/characters").
						uri("lb://PRUEBAS-DRAGON-BALL-FAILOVER"))
				.build();
	}

}
