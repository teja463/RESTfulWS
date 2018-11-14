package site.tejap;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class BookDao {
	private Map<String, Book> books;
	
	BookDao(){
		books = new ConcurrentHashMap<>();
		
		Book book1 = new Book("Book1", "Teja","997-234-343",new Date(), "1");
		Book book2 = new Book("Book2", "Pramod","848-484-292",new Date(), "2");
		
		books.put(book1.getId(), book1);
		books.put(book2.getId(), book2);
	}
	
	Collection<Book> getBooks(){
		return (books.values());
	}
	
	Book getBook(String id) throws BookNotFoundException{
		if(books.containsKey(id)){
			return (books.get(id));
		}
		
		throw new BookNotFoundException("Book with the id: "+id+" not found");
	}
	
	Book addBook(Book book){
		book.setId(UUID.randomUUID().toString());;
		books.put(book.getId(), book);
		return book;
	}

}
