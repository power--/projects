package com.goparty.data.mongodb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.goparty.data.interfaces.EventRepository;
import com.goparty.data.model.Event;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class EventRepositoryImplTest extends TestCase{
	
	private EventRepository repository;
	
	public EventRepositoryImplTest(String theTestName)
	{
		super(theTestName);
		repository = new EventRepositoryImpl();
	}
	
    public static Test suite()
    {
        return new TestSuite( EventRepositoryImplTest.class );
    }
    
    public void testAddGetEvent()
    {
    	Event aEvent = new Event();
    	aEvent.title = "HaiNan Travel";
    	aEvent.category = "Travel";
    	aEvent.description = "Let's travel to Hainan";
    	aEvent.endTime = Date.valueOf("2013-12-31");
    	aEvent.startTime = Date.valueOf("2013-12-20");
    	aEvent.owner = "Roy";
    	aEvent.attendee = new ArrayList<String>();
    	aEvent.attendee.add("Peter");
    	aEvent.attendee.add("Rongji");
    	aEvent.attendee.add("Wei");
    	aEvent.location = new double[]{110.1,100.1};
    	aEvent.status = "Not Start";
    	repository.AddEvent(aEvent);
    	
    	Assert.assertNotNull(aEvent.getId());
    	List<Event> aResult = repository.findEventsByUser("Roy");
    	Assert.assertTrue(aResult.size() > 0);
    	aResult = repository.findEventsByUser("Peter");
    	Assert.assertTrue(aResult.size() > 0);
    	repository.DeleteEvent(aEvent.getId());
    }
}
