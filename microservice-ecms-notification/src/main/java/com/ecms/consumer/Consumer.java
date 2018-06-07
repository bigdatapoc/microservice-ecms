package com.ecms.consumer;

import java.io.IOException;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecms.entity.Mail;
import com.ecms.service.NotificationService;

@Component
public class Consumer {
	
	private static Logger log = LoggerFactory.getLogger(Consumer.class);
	
	@Autowired
	private NotificationService notificationService;
	
	@RabbitListener(queues = "${jsa.rabbitmq.queue}", containerFactory = "jsaFactory")
	public void recievedMessage(Mail mail) {
		log.info("Recieved Message: " + mail);
		String output=notificationService.sendMail(mail);
		log.info("Result : "+output);
	}
}