package com.mypetshop.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class MyPetShopApplication {

	public static void main(String[] args) {

		SpringApplication.run(MyPetShopApplication.class, args);
	}

}
