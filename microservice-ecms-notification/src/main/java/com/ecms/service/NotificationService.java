package com.ecms.service;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.ecms.entity.Mail;

@Service
public class NotificationService {

	@Autowired
	private JavaMailSender javamailSender;

	@Autowired
	private SpringTemplateEngine templateEngine;

	private static Logger log = LoggerFactory.getLogger(NotificationService.class);

	public String sendMail(Mail mail) {

		log.info("ServiceMethod : Sending Email with Thymeleaf HTML Template Example");
		String response = "Mail Sending Failed";
		MimeMessage message = javamailSender.createMimeMessage();
		MimeMessageHelper helper = null;
		try {
			helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			// helper.addAttachment("logo.jpg", new ClassPathResource("download.jpg"));
			Context context = new Context();
			context.setVariables(mail.getTemplateVariableMap());
			String html = templateEngine.process("email-template", context);
			helper.setTo(mail.getTo());
			helper.setText(html, true);
			helper.setSubject(mail.getSubject());
			helper.setFrom(mail.getFrom());
			javamailSender.send(message);
			response = "Mail Has Been Successfully Send to : " + mail.getTo();
		} catch (MessagingException e) {
			response = "There Seems to be an error in Sending Mail, Please Check";
			e.printStackTrace();
		}
		return response;
	}

}
