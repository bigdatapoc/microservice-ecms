package com.ecms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
/*
 * Owner: @Himanshu_Nagpal
 * Entry Point of Application
 */
@EnableSwagger2
@SpringBootApplication
// @EnableDiscoveryClient
public class MicroserviceEcmsNotificationApplication {

	public static void main(String[] args) 	{	
		SpringApplication.run(MicroserviceEcmsNotificationApplication.class, args);
	}
	
}
