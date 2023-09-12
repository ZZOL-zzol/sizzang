package com.zzol.sizzang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SizzangApplication {

	public static void main(String[] args) {
		SpringApplication.run(SizzangApplication.class, args);
	}

}
