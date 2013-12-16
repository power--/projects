package com.goparty.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;

import org.springframework.stereotype.Service;

import com.goparty.exception.meta.LocationBaseException;

@Service("genericExceptionMapper")
public class GenericExceptionMapper implements ExceptionMapper<LocationBaseException> {

	@Override
	public Response toResponse(LocationBaseException exception) {
		 ResponseBuilder rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR); 
		 rb.type("application/json;charset=UTF-8");
		 rb.entity(exception);  
		 Response r = rb.build();       
		 return r;
	}

}
