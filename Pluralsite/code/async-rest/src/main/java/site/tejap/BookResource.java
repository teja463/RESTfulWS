package site.tejap;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("books")
public class BookResource {

	BookDao bookDao = new BookDao();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Book> getBooks(){
		return bookDao.getBooks();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Book getBook(@PathParam("id") String id){
		return bookDao.getBook(id);
	}
	
}
