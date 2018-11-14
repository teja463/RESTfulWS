package site.tejap;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BookNotFoundMapper implements ExceptionMapper<BookNotFoundException>{

	@Override
	public Response toResponse(BookNotFoundException arg0) {
		return Response.status(404).entity(arg0.getMessage()).build();
	}

}
