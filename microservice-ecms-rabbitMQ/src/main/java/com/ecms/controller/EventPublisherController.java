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

import com.ecms.entity.Mail;
import com.ecms.service.EventPublisherService;

@RestController
@RequestMapping("/")
public class EventPublisherController {

	private static Logger log = LoggerFactory.getLogger(EventPublisherController.class);

	@Autowired
	EventPublisherService eventPublisherService;

	@RequestMapping(value = "/rabbitMq", consumes = "application/json")
	public ResponseEntity<?> welcome(@RequestBody Mail mail) throws IOException, TimeoutException {
		log.info("Logging Message => In EventPublisherController: " + mail);
		String output = eventPublisherService.publishEvent(mail);
		return ResponseEntity.ok(output);
	}
}
