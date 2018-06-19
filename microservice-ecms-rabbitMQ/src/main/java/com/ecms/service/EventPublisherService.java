package com.ecms.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecms.entity.Mail;
import com.ecms.producer.Producer;

/**
 * Business Logic for sending notification
 * @author nagpalh
 *
 */
@Service
public class EventPublisherService {
	private static Logger log = LoggerFactory.getLogger(EventPublisherService.class);
	@Autowired
	private Producer producer;

	/**
	 * function that uses Producer for producing message to RabbitMq exchange(or Queue in layman language)
	 * @param mail
	 * @return String
	 */
	public String publishEvent(Mail mail) {
		log.info("In Event Publisher Service , Ready to Pass message to 'RabbitMQ Producer' for sending it to Exchange");
		String response;
		try {
			response = producer.produce(mail);
		} catch (IOException | TimeoutException e) {
			response = "Exception Occurred Unable to Produce Data on RabbitMq";
			log.info("Error Message: " + e.getMessage() + "\nException Class: " + e.getClass()
					+ "\nCause of Exception: " + e.getCause() + "\nStack Trace oF Exception: " + e.getStackTrace());
		}
		return response;
	}

}
