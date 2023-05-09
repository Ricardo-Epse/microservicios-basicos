package com.example.client.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PruebasLoadBalancerConfiguration {
	
	private static final Logger log = LoggerFactory.getLogger(PruebasLoadBalancerConfiguration.class);
	
	@Bean
	public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(
			ConfigurableApplicationContext context) {
			log.info("Configuring Load balancer to prefer same instance");
				return ServiceInstanceListSupplier
						.builder()
						.withBlockingDiscoveryClient()
						//.withSameInstancePreference() // Misma instancia recibe todo el trafico
						.build(context);
	}
}
