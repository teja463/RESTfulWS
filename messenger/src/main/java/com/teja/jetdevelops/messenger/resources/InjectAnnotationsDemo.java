package com.teja.jetdevelops.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/injectdemo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InjectAnnotationsDemo {

	@GET
	@Path("annotations")
	public String annotationsDemo(@MatrixParam("param") String value, @HeaderParam("Accept") String accept){
		return "Matrix Param: "+value+" Accepts: "+accept;
	}
}

