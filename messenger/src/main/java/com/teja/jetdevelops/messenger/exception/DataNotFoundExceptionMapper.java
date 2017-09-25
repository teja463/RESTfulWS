package com.teja.jetdevelops.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.teja.jetdevelops.messenger.model.ErrorMessage;


public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage er = new ErrorMessage(400, ex.getMessage());
		return Response.status(Status.NOT_FOUND).entity(er).build();
	}

}
