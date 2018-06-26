package com.ecms.service;

import javax.mail.MessagingException;

import com.ecms.model.Event;


public interface NotificationService
{
   String sendMail(Event event) throws MessagingException;
    
//    void findEvent();
//    
//    void findTemplate();
//    
//    void findUsers();
//    
//    void prepareMessage();
//    
//    void sendMessage();

}
