package site.tejap;

import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class PoweredByFilter implements ContainerResponseFilter{

	@Override
	public void filter(ContainerRequestContext arg0, ContainerResponseContext responseContext) throws IOException {
		for( Annotation a : responseContext.getEntityAnnotations()){
			if (a.annotationType() == PoweredBy.class){
				String value = ((PoweredBy) a).value();
				responseContext.getHeaders().add("X-Powered-By", value);
			}
		}
	}

}
