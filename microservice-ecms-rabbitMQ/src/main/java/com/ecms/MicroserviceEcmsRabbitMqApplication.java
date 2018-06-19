package com.ecms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Entry Point For Application(Producing Data on RabbitMq Queue) 
 * @author nagpalh
 */
@EnableSwagger2
@SpringBootApplication
//@EnableDiscoveryClient
public class MicroserviceEcmsRabbitMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceEcmsRabbitMqApplication.class, args);
	}

}
