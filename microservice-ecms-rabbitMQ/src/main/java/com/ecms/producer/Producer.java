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

@Component
public class Producer {

	private static Logger log = LoggerFactory.getLogger(MicroserviceEcmsRabbitMqApplication.class);

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Value("${jsa.rabbitmq.exchange}")
	private String exchange;

	@Value("${jsa.rabbitmq.routingkey}")
	private String routingkey;

	public void produce(Mail mail) throws IOException, TimeoutException {
		log.info("In Producer(Ready For Producing Data to RabbitMq) : " + mail);
		amqpTemplate.convertAndSend(exchange, routingkey, mail);
	}
}