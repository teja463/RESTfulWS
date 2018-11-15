package site.tejap;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.HttpMethodOverrideFilter;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.jaxrs.xml.JacksonXMLProvider;

public class BookApplication extends ResourceConfig {

	JacksonJsonProvider json = new JacksonJsonProvider().
			configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).
			configure(SerializationFeature.INDENT_OUTPUT, true);

	JacksonXMLProvider xml = new JacksonXMLProvider().
			configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
			.configure(SerializationFeature.INDENT_OUTPUT, true);
	

	public BookApplication(final BookDao dao) {
		packages("site.tejap").register(new AbstractBinder() {

			@Override
			protected void configure() {
				bind(dao).to(BookDao.class);

			}
		}).register(json)
		.register(xml)
		.register(HttpMethodOverrideFilter.class);
		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
	}
}
