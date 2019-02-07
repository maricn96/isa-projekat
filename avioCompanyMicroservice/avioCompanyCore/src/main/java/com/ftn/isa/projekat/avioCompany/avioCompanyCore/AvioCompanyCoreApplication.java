package com.ftn.isa.projekat.avioCompany.avioCompanyCore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication()
@EnableFeignClients()
public class AvioCompanyCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvioCompanyCoreApplication.class, args);
	}
}
