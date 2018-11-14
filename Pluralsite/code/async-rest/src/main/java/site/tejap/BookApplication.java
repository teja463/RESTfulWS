package site.tejap;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class BookApplication extends ResourceConfig {

	JacksonJsonProvider json = new JacksonJsonProvider().configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	public BookApplication(final BookDao dao) {
		packages("site.tejap").register(new AbstractBinder() {

			@Override
			protected void configure() {
				bind(dao).to(BookDao.class);

			}
		}).register(json);
	}
}