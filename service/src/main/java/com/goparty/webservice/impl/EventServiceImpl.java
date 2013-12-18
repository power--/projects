package com.goparty.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.goparty.model.Event;
import com.goparty.model.EventCategory;
import com.goparty.model.User;
import com.goparty.webservice.EventService;

@Service("eventService")
public class EventServiceImpl implements EventService {

	@Override
	public Event getEvent(String id) {
		Event ret = new Event();
		ret.setId(id);
		
		List<User> attendees = new ArrayList<User>();
		
		User user = new User();
		user.setId("001");
		user.setUserName("hello world1");
		attendees.add(user);
		
		user = new User();
		user.setId("002");
		user.setUserName("hello world2");
		attendees.add(user);
		ret.setAttendees(attendees);
		
		EventCategory category = new EventCategory();
		category.setId("001");
		category.setName("draft");
	    ret.setEventCategory(category);
		
		
		ret.setAttendees(attendees);
		
		return ret;
	}

	@Override
	public Event addEvent(Event event) {
		event.setId(java.util.UUID.randomUUID().toString());
		return event;
	}

	@Override
	public Event updateUser(Event event) {
		return event;
	}

	@Override
	public boolean deleteEvent(String id) {
		return false;
	}
}
