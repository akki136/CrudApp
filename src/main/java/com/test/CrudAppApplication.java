package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@ComponentScan(basePackages={"com.test.serviceImpl","com.test.controller","com.test.entity","com.test.Dao"})
@SpringBootApplication
public class CrudAppApplication {

	

	public static void main(String[] args) {
		SpringApplication.run(CrudAppApplication.class, args);
	}

	
	

	
}
