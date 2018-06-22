package com.ecms.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ecms.entity.Event;
import com.ecms.entity.EventTemplate;
import com.ecms.entity.User;

@Repository
public class NotificationDao {

	private static Logger log = LoggerFactory.getLogger(NotificationDao.class);
	
	@PersistenceContext
	private EntityManager em;

	public List<User> getUsers(Event event) {
		String query="select h from user h where h.event_id=" + event.getEvent_id();
		List<User> someUser = em.createQuery(query, User.class).getResultList();
		return someUser;
	}

	public EventTemplate getEventTemplate(Event event) {
		EventTemplate eventTempalte = em
				.createQuery("select h from event_template h where h.event_id =" + event.getEvent_id(),
						EventTemplate.class)
				.getSingleResult();
		return eventTempalte;
	}

}
