package com.goparty.data.mongodb;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.goparty.data.config.SpringMongoConfig;
import com.goparty.data.interfaces.*;
import com.goparty.data.model.Event;
public class EventRepositoryImpl implements EventRepository {

private MongoOperations mongoOperation;
	
	public EventRepositoryImpl()
	{
    	ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");
	}
	
	public List<Event> findEventsByUser(String theUserId)
	{
		List<Event> aCreatedEvents =  mongoOperation.find(
				new Query(Criteria.where("owner").is(theUserId)), Event.class);
		
		List<Event> aAttendeeEvents = mongoOperation.find(
				new Query(Criteria.where("attendee").elemMatch(new Criteria().is(theUserId))), Event.class);
		
		return union(aCreatedEvents, aAttendeeEvents);
	}
	
	public Event findEventById(String theId)
	{
		return mongoOperation.findById(theId, Event.class);
	}
	
	public void AddEvent(Event theEvent)
	{
		mongoOperation.insert(theEvent);
	}
	
	public void UpdateEvent(Event theEvent)
	{
		Query aEventQuery = new Query(Criteria.where("id").is(
				theEvent.getId()));
		Update aEventUpdate = new Update();
		aEventUpdate.set("title", theEvent.title);
		mongoOperation.updateFirst(aEventQuery, aEventUpdate,
				Event.class);
	}
	
	public void DeleteEvent(String theId)
	{
		Query aEventQuery = new Query(Criteria.where("id").is(
				theId));
		mongoOperation.remove(aEventQuery, Event.class);
	}
	
    private <T> List<T> union(List<T> list1, List<T> list2) {
        Set<T> set = new HashSet<T>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<T>(set);
    }
}
