package site.tejap;

import static org.junit.Assert.*;

import java.util.Collection;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

public class BookResourceTest extends JerseyTest {

	protected Application configure(){
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig().packages("site.tejap");
	}
	
	
	@Test
	public void testGetBook(){
		Book book = target("books").path("1").request().get(Book.class);
		assertNotNull(book);
	}
	
	public void testGetBooks(){
		Collection<Book> collection = target("books").request().get(new GenericType<Collection<Book>>() {});
		assertEquals(2, collection.size());
	}
}
