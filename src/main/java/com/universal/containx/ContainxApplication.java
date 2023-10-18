package com.universal.containx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.universal.containx.model"})
public class ContainxApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContainxApplication.class, args);
	}

}
