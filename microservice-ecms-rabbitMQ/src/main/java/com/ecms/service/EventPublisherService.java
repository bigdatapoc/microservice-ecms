package com.ecms.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecms.entity.Mail;
import com.ecms.producer.Producer;

@Service
public class EventPublisherService {
	private static Logger log = LoggerFactory.getLogger(EventPublisherService.class);
	@Autowired
	private Producer producer;

	public String publishEvent(Mail mail) {
		String response = "Unable to Produce Data on RabbitMq";
		try {
			log.info("In  publishEvent() of EventPublisherService.class\n Data : " + mail);
			producer.produce(mail);
			response = "Data Produces on RabbitMq Successfully";
		} catch (IOException | TimeoutException e) {
			log.info("Exception Occurred");
			response = "Exception Occurred Unable to Produce Data on RabbitMq";
			e.printStackTrace();
		}

		return response;
	}

}
