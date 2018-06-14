package com.ecms.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.StandardTemplateModeHandlers;
import java.nio.charset.StandardCharsets;


/*
 * Owner: @Himanshu_Nagpal
 * This Class Acts as Configuration For Thymeleaf Templating Engine. 
 */
@EnableWebMvc
@ComponentScan(basePackages = "com.ecms")
@Configuration
public class ThymeleafConfig {

	/*
	 * This Function Create Object that will Process Templates(or HTML Pages).  
	 */
	@Bean(name = "templateEngine")
	public SpringTemplateEngine springTemplateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addTemplateResolver(htmlTemplateResolver());
		return templateEngine;
	}

	/*
	 * This Function is used in creating SpringTemplateEngine Object. Which is further used in processing Templates(Or Html Pages)
	 */
	@Bean
	public SpringResourceTemplateResolver htmlTemplateResolver() {
		SpringResourceTemplateResolver emailTemplateResolver = new SpringResourceTemplateResolver();
		emailTemplateResolver.setPrefix("classpath:/templates/");
		emailTemplateResolver.setSuffix(".html");
		emailTemplateResolver.setTemplateMode(StandardTemplateModeHandlers.HTML5.getTemplateModeName());
		emailTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
		return emailTemplateResolver;
	}
}