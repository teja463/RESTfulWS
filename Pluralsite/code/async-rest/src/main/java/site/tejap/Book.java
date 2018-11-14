package site.tejap;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonPropertyOrder({"id"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JacksonXmlRootElement(localName="book")
public class Book {

	@NotNull(message="Title is required")
	private String title;
	@NotNull(message="Author is required")
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
	
	@JacksonXmlProperty(isAttribute=true)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
