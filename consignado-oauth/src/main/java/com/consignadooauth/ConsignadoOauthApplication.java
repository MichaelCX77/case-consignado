package com.consignadooauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class ConsignadoOauthApplication {

	public static void main(String[] args) {
		System.out.println("Senha padrao:" + new BCryptPasswordEncoder().encode("123456"));
		SpringApplication.run(ConsignadoOauthApplication.class, args);

	}

}
