package com.emp.mgmt.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EmployeeNotFoundExceptionMapper implements ExceptionMapper<EmployeeNotFoundException>{

	@Override
	public Response toResponse(EmployeeNotFoundException enf) {
		return Response.status(404).entity(enf.getMessage()).build();
	}

}
