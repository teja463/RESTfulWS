package site.tejap;

import static org.junit.Assert.*;

import java.util.Collection;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

public class BookResourceTest extends JerseyTest {

	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		final BookDao dao = new BookDao();
		return new BookApplication(dao);
	}

	@Test
	public void testGetBook() {
		Book book = target("books").path("1").request().get(Book.class);
		assertNotNull(book);
	}

	@Test
	public void testGetBooks() {
		Collection<Book> collection = target("books").request().get(new GenericType<Collection<Book>>() {
		});
		assertEquals(2, collection.size());
	}
	
	@Test
	public void addBookTest(){
		Book book = new Book();
		book.setTitle("Added book");
		book.setAuthor("Jersey Test");
		Entity<Book> bookEntity = Entity.entity(book, MediaType.APPLICATION_JSON);
		Response response = target("books").request().post(bookEntity);
		Book responseBook = response.readEntity(Book.class);
		assertEquals(200, response.getStatus());
		assertEquals("Added book", responseBook.getTitle());
		
	}
}
