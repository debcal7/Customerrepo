package com.gaming.cust;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={"com.gaming.cust"})
public class CustomerMain {

	public static void main(String[] args) {
		SpringApplication.run(CustomerMain.class, args);
	}
	
}
