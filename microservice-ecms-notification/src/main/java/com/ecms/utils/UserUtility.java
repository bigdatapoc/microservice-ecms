package com.ecms.utils;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecms.dao.UserDao;
import com.ecms.model.Event;
import com.ecms.model.User;

public class UserUtility
{
    @Autowired
    private UserDao userDao;

    public User getUser(Event event)
    {
        User mailUser = userDao.getUser(event.getEventId());   
        return mailUser;
    }

}
