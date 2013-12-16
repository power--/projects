package com.goparty.data.interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.goparty.data.model.Event;

public interface EventRepository {

	public List<Event> findEventsByUser(String theUserId);
	
	public Event findEventById(String theId);
	
	public void AddEvent(Event theEvent);
	
	public void UpdateEvent(Event theEvent);
	
	public void DeleteEvent(String theId);
}
