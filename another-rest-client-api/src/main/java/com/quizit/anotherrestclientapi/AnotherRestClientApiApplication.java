package com.quizit.anotherrestclientapi;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class AnotherRestClientApiApplication implements CommandLineRunner {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private EurekaClient eurekaClient;

	public static void main(String[] args) {
		SpringApplication.run(AnotherRestClientApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		log.info("eureka client apps: {}", this.eurekaClient.getApplications().getRegisteredApplications());
		List<InstanceInfo> instanesRestClientApi = this.eurekaClient.getApplication("rest-client-api").getInstances();
		instanesRestClientApi.stream().forEach(i -> log.info("instance: {}",i.getId()));
		String clinetRestId = instanesRestClientApi.get(0).getId();

		while (true) {
			ResponseEntity<String> response = restTemplate.exchange("http://rest-client-api/instance_id", HttpMethod.GET, null, String.class);
			log.info("response: {}", response);
			Thread.sleep(2000);
		}
	}
}

