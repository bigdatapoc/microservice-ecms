package com.ecms;

import java.io.FileNotFoundException;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;

import org.modeshape.common.collection.Problems;
import org.modeshape.jcr.ConfigurationException;
import org.modeshape.jcr.ModeShapeEngine;
import org.modeshape.jcr.RepositoryConfiguration;
import org.modeshape.schematic.document.ParsingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class MicroserviceEcmsFilesystemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceEcmsFilesystemServiceApplication.class, args);
	}

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName())).paths(PathSelectors.any())
				.build().apiInfo(generateApiInfo());
	}

	private ApiInfo generateApiInfo() {
		return new ApiInfo("Transcoding Service", "This service is to.", "Version 1.0 - mw", "urn:tos",
				"transcoding@hcl.com", "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	}

	@Bean
	public ModeShapeEngine engin() {
		return new ModeShapeEngine();
	}

	@Bean
	public Repository repository(ModeShapeEngine engin)
			throws ParsingException, FileNotFoundException, ConfigurationException, RepositoryException {

		engin.start();
		RepositoryConfiguration config = RepositoryConfiguration.read("D:\\Users\\nagpalh\\ecms\\microservice-ecms\\microservice-ecms-filesystem-service\\src\\main\\resources\\repository-json.json");
		config = config.withName("test Repository");

		Problems problems = config.validate();
		if (problems.hasErrors()) {
			System.err.println("Problems with the configuration.");
			System.err.println(problems);
			System.exit(-1);
		}

		return engin.deploy(config);

	}

	/*@Bean
	public Session session(Repository repository) throws LoginException, NoSuchWorkspaceException, RepositoryException {
		return repository.login("ecmsWorkspace");
	}*/

}
