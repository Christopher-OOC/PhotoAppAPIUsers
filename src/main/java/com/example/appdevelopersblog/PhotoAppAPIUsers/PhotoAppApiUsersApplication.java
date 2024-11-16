package com.example.appdevelopersblog.PhotoAppAPIUsers;

import com.example.appdevelopersblog.PhotoAppAPIUsers.shared.FeignErrorDecoder;
import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class PhotoAppApiUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoAppApiUsersApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	public HttpExchangeRepository httpExchangeRepository() {

		return new InMemoryHttpExchangeRepository();
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {

		return new RestTemplate();
	}

	@Bean
	public Logger.Level feignLogger() {

		return Logger.Level.FULL;
	}

	@Bean
	public FeignErrorDecoder feignErrorDecoder() {

		return new FeignErrorDecoder();
	}

}
