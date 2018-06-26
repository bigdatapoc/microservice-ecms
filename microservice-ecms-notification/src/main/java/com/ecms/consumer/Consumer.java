package com.ecms.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecms.constants.MessageConstants;
import com.ecms.model.Event;
import com.ecms.notification.factory.NotificationFactory;
import com.ecms.notification.strategy.NotificationStrategy;
import com.ecms.service.NotificationService;

/**
 * This Class Acts as Consumer (Or Listener). and will Listen to The messages on
 * queue.
 * 
 * @author nagpalh
 */
@Component
@EnableRabbit
public class Consumer
{
    private static Logger log = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private NotificationService notificationService;


    /**
     * This Function Listens to the messages on given queues.
     * 
     * @param EventDao
     */
    //    @RabbitListener(queues = "${mail.rabbitmq.queue}", containerFactory = "mailFactory")
    //    public void recievedMessage(Event event)
    //    {
    //        log.info(MessageConstants.Enter_Consumer + event);
    //        CompletableFuture<String> output = notificationService.sendMail(event);
    //        log.info(MessageConstants.Call_After_Asynch + output);
    //    }

    @RabbitListener(queues = "${mail.rabbitmq.queue}", containerFactory = "mailFactory")
    public void recievedMessage(Event event)
    {
        String typeOfMessage = "Mail";
        log.info(MessageConstants.Enter_Consumer + event);
        NotificationStrategy notificationStrategy = NotificationFactory.getNotificationStrategy(typeOfMessage);
        String output = notificationStrategy.sendMessage(event);//notificationService.sendMail(event);
            log.info(MessageConstants.Call_After_Asynch + output);
    }

}
