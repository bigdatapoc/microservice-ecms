package com.ecms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecms.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>
{
    @Query("select h from User h where h.eventId= ?1")
    List<User> getUsers(Integer eventid);

}
