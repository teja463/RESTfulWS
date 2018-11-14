package site.tejap;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ManagedAsync;

@Path("books")
public class BookResource {

	@Context BookDao dao;
	
	@GET
	@Produces({"application/json;qs=1","application/xml;qs=0.5"})
	@ManagedAsync
	public void getBooks(@Suspended AsyncResponse response){
		response.resume(dao.getBooks());
	}
	
	@GET
	@Path("/{id}")
	@Produces({"application/json;qs=1","application/xml;qs=0.5"})
	@ManagedAsync
	public void getBook(@PathParam("id") String id, @Suspended AsyncResponse response){
		try {
			Book book = dao.getBook(id);
			response.resume(book);
		} catch (BookNotFoundException e) {
			response.resume(e);
		}
	}
	
	@POST
	@Produces({"application/json;qs=1","application/xml;qs=0.5"})
	@Consumes(MediaType.APPLICATION_JSON)
	@ManagedAsync
	public void addBook(@Valid @NotNull Book book, @Suspended AsyncResponse response ){
		response.resume(dao.addBook(book));
	}
	
}
