package com.ecms.service;

import java.util.concurrent.CompletableFuture;

import com.ecms.entity.Event;

public interface Notification {
	public CompletableFuture<String> sendMail(Event event);
	
}