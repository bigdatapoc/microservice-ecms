package com.ecms.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ecms.entity.Mail;
import com.ecms.service.NotificationService;

/*
 * Owner: @Himanshu_Nagpal
 * This Class Acts as Consumer (Or Listener).
 * In this Class we will Listen to The messages on queue.
 */
@Component
@EnableRabbit
public class Consumer {

	private static Logger log = LoggerFactory.getLogger(Consumer.class);

//	@Value("${mail.rabbitmq.queue}")
//	public String queueName;

	@Autowired
	private NotificationService notificationService;

	@RabbitListener(queues = "${mail.rabbitmq.queue}", containerFactory = "mailFactory")
	public void recievedMessage(Mail mail) {

		log.info("Listener => Recieved Message: " + mail);
		String output = notificationService.sendMail(mail);
		log.info("Result : " + output);
	}
}