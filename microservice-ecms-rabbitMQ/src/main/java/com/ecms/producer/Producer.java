package com.ecms.producer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ecms.MicroserviceEcmsRabbitMqApplication;
import com.ecms.entity.Mail;
/**
 * This class will act as producer and will send notification on RabbitMQ Queue
 * @author nagpalh
 *
 */
@Component
public class Producer {

	private static Logger log = LoggerFactory.getLogger(MicroserviceEcmsRabbitMqApplication.class);

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Value("${mail.rabbitmq.exchange}")
	private String exchange;

	@Value("${mail.rabbitmq.routingkey}")
	private String routingkey;

	/**
	 * 	Just for sending mail object to specific "exchange" with specified "routing key".
	 * 		Now, that routing key is used by exchanges for routing message to the specific "Queue". 
	 * 			
	 * @param mail
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public String produce(Mail mail) throws IOException, TimeoutException {
		log.info("In Producer(Ready For Producing Data to RabbitMq)");
		amqpTemplate.convertAndSend(exchange, routingkey, mail);
		return "Data Produces on RabbitMq Successfully";
	}
}