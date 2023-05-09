package com.example.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;

import com.example.client.clients.DragonBallCharacterClient;
import com.netflix.discovery.EurekaClient;


@SpringBootApplication
@EnableFeignClients
public class PruebaClientStandalon1Application implements ApplicationRunner{
	
	@Autowired
	private DragonBallCharacterClient dragonBallClient;
	
	@Autowired
	private EurekaClient eurekaClient;
	
	private static final Logger log = LoggerFactory.getLogger(PruebaClientStandalon1Application.class);

	public static void main(String[] args) {
		SpringApplication.run(PruebaClientStandalon1Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		for (int i = 0; i < 10 ; i++) {
			ResponseEntity<String> responseEntity = dragonBallClient.getApplicationName();
			log.info("Status {} ", responseEntity.getStatusCode());
			String body = responseEntity.getBody();
			log.info("body {}", body);
		}

	}

//	@Override
//	public void run(ApplicationArguments args) throws Exception {
//		Application application = eurekaClient.getApplication("pruebas-dragon-ball");
//		log.info("application name {}",application.getName());
//		List<InstanceInfo> instances = application.getInstances();
//		
//		for( InstanceInfo instanceInfo : instances ) {
//			log.info("Ip {} , puerto {}", instanceInfo.getIPAddr() , instanceInfo.getPort());
//		}
//	}

}
