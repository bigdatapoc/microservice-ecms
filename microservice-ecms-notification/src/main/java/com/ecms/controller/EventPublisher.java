package com.ecms.controller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecms.constants.ApplicationConstants;
import com.ecms.entity.Mail;
import com.ecms.service.Producer;

/**
 * Rest Controller for incoming request for sending mail
 * 
 * @author nagpalh
 * Need to be deleted.
 */
@RestController
@RequestMapping("/")
public class EventPublisher {

	private static Logger log = LoggerFactory.getLogger(EventPublisher.class);

	/*
	 * @Autowired EventPublisherService eventPublisherService;
	 */
	@Autowired
	Producer producer;

	/**
	 * This Function route the message to service class welcome() function
	 * 
	 * @param mail
	 * @return String
	 * @throws IOException
	 * @throws TimeoutException
	 */
	@RequestMapping(value = "/message", consumes = "application/json")
	public ResponseEntity<?> welcome(@RequestBody Mail mail) throws IOException, TimeoutException {
		log.info(ApplicationConstants.Enter_Controller + mail);
		String output = producer.produce(mail);
		log.info(output);
		return ResponseEntity.ok(output);
	}

}
