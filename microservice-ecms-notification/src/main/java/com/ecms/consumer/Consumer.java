package com.ecms.consumer;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecms.entity.Mail;
import com.ecms.service.NotificationService;

/**
 * This Class Acts as Consumer (Or Listener). and will Listen to The messages on queue.
 * @author nagpalh
 */
@Component
@EnableRabbit
public class Consumer {

	private static Logger log = LoggerFactory.getLogger(Consumer.class);

	@Autowired
	private NotificationService notificationService;

	/**
	 * This Function Listens to the messages on given queues.
	 * @param mail
	 */
	@RabbitListener(queues = "${mail.rabbitmq.queue}", containerFactory = "mailFactory")
	public void recievedMessage(Mail mail) {

		log.info("In Consumer, Listener Recieved Message: " + mail);
		CompletableFuture<String> output = notificationService.sendMail(mail);
		log.info("Mail passed to asynch thread");
	}
}