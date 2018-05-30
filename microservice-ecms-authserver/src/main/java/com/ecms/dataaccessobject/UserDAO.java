package com.ecms.dataaccessobject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.ecms.domainobject.UserDO;

@Repository
public class UserDAO
{

    @Autowired
    JdbcTemplate jdbcTemplate;


    public UserDO getUserDetails(String username)
    {

        Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM USERS WHERE USERNAME=?";
        List<UserDO> list = jdbcTemplate.query(sqlQuery, new String[] {username}, (ResultSet rs, int rowNum) -> {
            UserDO userModel = new UserDO();
            userModel.setUsername(username);
            userModel.setPassword(rs.getString("PASSWORD"));
            return userModel;
        });

        if (list != null && list.size() > 0)
        {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_SYSTEMADMIN");
            grantedAuthoritiesList.add(grantedAuthority);
            list.get(0).setGrantedAuthoritiesList(grantedAuthoritiesList);
            return list.get(0);
        }

        return null;
    }
}
