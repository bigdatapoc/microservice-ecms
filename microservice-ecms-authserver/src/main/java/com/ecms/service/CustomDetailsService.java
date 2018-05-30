package com.ecms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecms.dataaccessobject.CustomUser;
import com.ecms.dataaccessobject.UserDAO;
import com.ecms.domainobject.UserDO;

@Service
public class CustomDetailsService implements UserDetailsService
{

    @Autowired
    UserDAO userDAO;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserDO userModel = userDAO.getUserDetails(username);
        CustomUser customUser = new CustomUser(userModel);
        return customUser;
    }

}
