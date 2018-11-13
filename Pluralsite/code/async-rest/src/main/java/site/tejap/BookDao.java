package site.tejap;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BookDao {
	private Map<String, Book> books;
	
	BookDao(){
		books = new HashMap<>();
		
		Book book1 = new Book("Book1", "Teja","997-234-343",new Date(), "1");
		Book book2 = new Book("Book2", "Pramod","848-484-292",new Date(), "2");
		
		books.put(book1.getId(), book1);
		books.put(book2.getId(), book2);
	}
	
	Collection<Book> getBooks(){
		return (books.values());
	}
	
	Book getBook(String id){
		return (books.get(id));
	}

}
