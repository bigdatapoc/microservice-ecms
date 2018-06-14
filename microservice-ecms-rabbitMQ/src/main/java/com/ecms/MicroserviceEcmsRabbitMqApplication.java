package com.ecms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
//@EnableDiscoveryClient
public class MicroserviceEcmsRabbitMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceEcmsRabbitMqApplication.class, args);
	}

}
