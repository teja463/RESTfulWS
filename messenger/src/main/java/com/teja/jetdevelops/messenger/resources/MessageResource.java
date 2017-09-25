package com.teja.jetdevelops.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.teja.jetdevelops.messenger.model.Message;
import com.teja.jetdevelops.messenger.resources.bean.MessageFilterBean;
import com.teja.jetdevelops.messenger.service.MessageService;

@Path("/messages")
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService msgService  = new MessageService();
	
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean bean){
		if(bean.getYear()>0){
			return msgService.getAllMessagesForYear(bean.getYear());
		}
		
		/*if(bean.getStart()>= 0 && bean.getSize() >= 0){
			return msgService.getMessagesByPagination(bean.getStart(), bean.getSize());
		}*/
		return msgService.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id){
		return msgService.getMessage(id);
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message){
		message.setId(id);
		return msgService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void removeMessage(@PathParam("messageId") long id){
		msgService.removeMessage(id);
	}
	
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo){
		
		Message newMessage = msgService.addMessage(message);
		URI build = uriInfo.getAbsolutePathBuilder().path(String.valueOf(newMessage.getId())).build();
		return Response.created(build).entity(newMessage).build();
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getComments(){
		return new CommentResource();
	}
}
