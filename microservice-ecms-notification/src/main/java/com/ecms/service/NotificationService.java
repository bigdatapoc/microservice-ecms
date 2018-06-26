package com.ecms.service;

import java.util.concurrent.CompletableFuture;

import com.ecms.model.Event;


public interface NotificationService
{
    CompletableFuture<String> sendMail(Event event);

}
