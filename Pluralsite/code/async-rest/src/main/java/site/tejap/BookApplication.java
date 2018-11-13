package site.tejap;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

public class BookApplication extends ResourceConfig {

	public BookApplication(final BookDao dao) {
		packages("site.tejap").register(new AbstractBinder() {

			@Override
			protected void configure() {
				bind(dao).to(BookDao.class);

			}
		});
	}
}
