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

import com.goparty.model.User;

@Path("/user/")
@WebService
@GZIP
public interface UserService {
	
	
	@WebMethod
	@GET
	@Path("{id}")
	@Descriptions({
		@Description(value = "returns a user by id", target = DocTarget.METHOD),
		@Description(value = "user of the id", target = DocTarget.RETURN)
	})
	public User getUser(@Description(value = "the id of the user") @PathParam("id") String id);
	
	@WebMethod
	@POST
	@Descriptions({
		@Description(value = "stores a new user data", target = DocTarget.METHOD),
		@Description(value = "the newly created user data", target = DocTarget.RETURN)
	})
	public User addUser(User user);
	
	@WebMethod
	@PUT
	@Descriptions({
		@Description(value = "updates or creates a new user data", target = DocTarget.METHOD),
		@Description(value = "the newly created or updated location data", target = DocTarget.RETURN)
	})
	public User updateUser(User user);
	
	@WebMethod
	@DELETE
	@Path("{location}")
	@Descriptions({
		@Description(value = "deletes a user data", target = DocTarget.METHOD),
		@Description(value = "the result of delete user action", target = DocTarget.RETURN)
	})
	public boolean deleteUser(@Description(value = "the id of the user") @PathParam("id")String id);
}
