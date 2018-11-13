package site.tejap;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("books")
public class BookResource {

	@Context BookDao dao;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Book> getBooks(){
		return dao.getBooks();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Book getBook(@PathParam("id") String id){
		return dao.getBook(id);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Book addBook(Book book){
		return dao.addBook(book);
	}
	
}
