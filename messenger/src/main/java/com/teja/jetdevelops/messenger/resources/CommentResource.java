package com.teja.jetdevelops.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.teja.jetdevelops.messenger.model.Comment;
import com.teja.jetdevelops.messenger.service.CommentService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {

	CommentService cmtService = new CommentService();
	
	
	@GET
	public List<Comment> getComments(@PathParam("messageId") long messageId){
		return cmtService.getAllComments(messageId);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId") long messageId ,@PathParam("commentId") long commentId){
		return cmtService.getComment(messageId, commentId);
	}
	
	@POST
	public Comment addComment(@PathParam("messageId") long messageId, Comment comment){
		return cmtService.addComment(messageId, comment);
	}
}
