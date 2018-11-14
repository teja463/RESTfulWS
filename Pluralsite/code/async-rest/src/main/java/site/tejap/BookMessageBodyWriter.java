package site.tejap;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@Provider
@Produces(MediaType.APPLICATION_XML)
public class BookMessageBodyWriter implements MessageBodyWriter<Collection<Book>>{

	@Context Providers providers;
	
	@JacksonXmlRootElement(localName="books")
	public class BookWrapper{
		
		@JacksonXmlProperty(localName = "book")
		@JacksonXmlElementWrapper(useWrapping=false)
		public Collection<Book> books;
		
		BookWrapper(Collection<Book> books){
			this.books = books;
		}
	}
	
	public long getSize(Collection<Book> books,Class<?> type ,Type genericType ,Annotation[] annotations,MediaType mediaType){
		return -1;
	}
	@Override
	public boolean isWriteable(Class<?> type, Type arg1, Annotation[] arg2, MediaType arg3) {
		return Collection.class.isAssignableFrom(type);
	}

	@Override
	public void writeTo(Collection<Book> books, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream outputStream) throws IOException, WebApplicationException {
		
		providers.getMessageBodyWriter(BookWrapper.class, genericType, annotations, mediaType).
				writeTo(new BookWrapper(books), type, genericType, annotations, mediaType, httpHeaders, outputStream);
	}

}
