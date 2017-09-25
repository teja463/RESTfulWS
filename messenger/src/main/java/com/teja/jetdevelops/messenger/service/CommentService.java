package com.teja.jetdevelops.messenger.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.teja.jetdevelops.messenger.database.DatabaseClass;
import com.teja.jetdevelops.messenger.model.Comment;
import com.teja.jetdevelops.messenger.model.ErrorMessage;
import com.teja.jetdevelops.messenger.model.Message;

public class CommentService {

	Map<Long, Message> messages = DatabaseClass.getAllMessages();
	MessageService ms = new MessageService();
	
	public CommentService(){
		Map<Long, Comment> comments = new HashMap<>();
		comments.put(1L, new Comment(1L,"test commet","Teja"));
		comments.put(2L, new Comment(2L,"test commet2","Teja2"));
		messages.get(1L).setComments(comments);;
		
		
	}
	
	
	public List<Comment> getAllComments(long messageId){
		Message message = messages.get(messageId);
		Response response = Response.status(Status.NOT_FOUND).entity(new ErrorMessage(404, "Not comments found for that id")).build();
		if(null == message){
			throw new NotFoundException(response);
		}
		Map<Long, Comment> comments = message.getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment addComment(long messageId, Comment comment){
		Message message = messages.get(messageId);
		System.out.println(message);
		Map<Long, Comment> comments = message.getComments();
		
		System.out.println(comments);
		comment.setId(Long.valueOf(comments.size()+1));
		comments.put(comment.getId(), comment);
		System.out.println(comment);
		
		message.setComments(comments);
		messages.put(messageId, message);
		return comment;
	}
	
	public Comment getComment(long messageId, long commentId){
		Message message = messages.get(messageId);
		return message.getComments().get(commentId);
	}
	
}
