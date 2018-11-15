package site.tejap;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.AssertTrue;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.grizzly.connector.GrizzlyConnectorProvider;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;

public class BookResourceTest extends JerseyTest {

	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		final BookDao dao = new BookDao();
		return new BookApplication(dao);
	}
	
	protected void configureClient(ClientConfig clientConfig){
		JacksonJsonProvider json = new JacksonJsonProvider();
		json.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		clientConfig.register(json);
		clientConfig.connectorProvider(new GrizzlyConnectorProvider());
	}

	Response addBook(String title, String author){
		Book book = new Book();
		book.setTitle(title);
		book.setAuthor(author);
		Entity<Book> bookEntity = Entity.entity(book, MediaType.APPLICATION_JSON);
		return target("books").request().post(bookEntity);
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
		Response response = addBook("Sample Title", "Sample Author");
		Book responseBook = response.readEntity(Book.class);
		assertEquals(200, response.getStatus());
		assertEquals("Sample Title", responseBook.getTitle());
		
	}
	
	@Test
	public void getBooksAsXml(){
		String output = target("books").request(MediaType.APPLICATION_XML).get().readEntity(String.class);
		XML xml = new XMLDocument(output);
//		System.out.println(xml.xpath("/books"));
	}
	
	@Test
	public void addBookWithoutTitle(){
		Response response = addBook(null, "Sample Author");
		assertEquals(400, response.getStatus());
		String output = response.readEntity(String.class);
		assertTrue(output.contains("Title is required"));
	}
	
	@Test
	public void addBookWithoutAuthor(){
		Response response = addBook("Sample Title", null);
		assertEquals(400, response.getStatus());
		String output = response.readEntity(String.class);
		assertTrue(output.contains("Author is required"));
	}
	
	@Test
	public void bookNotFound(){
		Response response = target("books").path("10").request().get();
		assertEquals(404, response.getStatus());
	}
	
	@Test
	public void updateBook(){
		Map<String, Object> book = new HashMap<>();
		book.put("author", "updated author");
		Entity<Map<String, Object>> entity = Entity.entity(book, MediaType.APPLICATION_JSON);
		Response response = target("books").path("/1").request().build("PATCH", entity).invoke();
		assertEquals(200, response.getStatus());
	}
}
