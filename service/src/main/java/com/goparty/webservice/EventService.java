package com.goparty.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.apache.cxf.annotations.GZIP;
import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

import com.goparty.model.Event;

@Path("/event/")
@WebService
@GZIP
public interface EventService {
	
	
	@WebMethod
	@GET
	@Path("{id}")
	@Descriptions({
		@Description(value = "returns a event by id", target = DocTarget.METHOD),
		@Description(value = "event of the id", target = DocTarget.RETURN)
	})
	public Event getEvent(@Description(value = "the id of the user") @PathParam("id") String id);
	
	@WebMethod
	@POST
	@Descriptions({
		@Description(value = "stores a new event data", target = DocTarget.METHOD),
		@Description(value = "the newly created event data", target = DocTarget.RETURN)
	})
	public Event addEvent(Event event);
	
	@WebMethod
	@PUT
	@Descriptions({
		@Description(value = "updates or creates a new event data", target = DocTarget.METHOD),
		@Description(value = "the newly created or updated event data", target = DocTarget.RETURN)
	})
	public Event updateUser(Event event);
	
	@WebMethod
	@DELETE
	@Path("{location}")
	@Descriptions({
		@Description(value = "deletes a user data", target = DocTarget.METHOD),
		@Description(value = "the result of delete user action", target = DocTarget.RETURN)
	})
	public boolean deleteEvent(@Description(value = "the id of the event") @PathParam("id")String id);
}
