package com.goparty.data.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "events")
public class Event {

	@Id
	private String id;
	
	public String title;
	
	public double[] location;
	
	public Date startTime;
	
	public Date endTime;
	
	public String description;
	
	public String category;
	
	public String status;
	
	public String owner;
	
	public List<String> attendee;   
	
	public String getId() {
		return id;
	}
}
