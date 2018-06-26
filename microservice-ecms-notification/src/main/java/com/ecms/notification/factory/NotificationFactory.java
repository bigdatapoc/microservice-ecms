package com.ecms.notification.factory;

import com.ecms.notification.strategy.NotificationStrategy;
import com.ecms.notification.strategyImpl.LiveNotificationStrategy;
import com.ecms.notification.strategyImpl.MailStrategy;
import com.ecms.notification.strategyImpl.SMSStrategy;

public class NotificationFactory
{
    public static NotificationStrategy getNotificationStrategy(String type)
    {
        if ("mail".equalsIgnoreCase(type))
            return new MailStrategy();
        else if ("sms".equalsIgnoreCase(type))
            return new SMSStrategy();
        else if ("live-notification".equalsIgnoreCase(type))
            return new LiveNotificationStrategy();
        return null;
    }
}
