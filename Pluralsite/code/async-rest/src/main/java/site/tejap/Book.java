package site.tejap;

import java.util.Date;

public class Book {

	private String title;
	private String author;
	private String isbn;
	private Date published;
	private String id;
	
	public Book(){
		
	}
	public Book(String title, String author, String isbn, Date published, String id) {
		super();
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.published = published;
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Date getPublished() {
		return published;
	}
	public void setPublished(Date published) {
		this.published = published;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
