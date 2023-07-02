package com.dds.currencies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@EnableScheduling
@SpringBootApplication
public class CurrenciesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrenciesApplication.class, args);
	}

}
