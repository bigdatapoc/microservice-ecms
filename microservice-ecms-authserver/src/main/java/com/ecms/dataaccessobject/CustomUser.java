package com.ecms.dataaccessobject;

import org.springframework.security.core.userdetails.User;

import com.ecms.domainobject.UserDO;

public class CustomUser extends User
{

    private static final long serialVersionUID = 1L;


    public CustomUser(UserDO userModel)
    {
        super(userModel.getUsername(), userModel.getPassword(), userModel.getGrantedAuthoritiesList());
    }
}
