package com.teja.jetdevelops.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.teja.jetdevelops.messenger.database.DatabaseClass;
import com.teja.jetdevelops.messenger.exception.DataNotFoundException;
import com.teja.jetdevelops.messenger.model.Message;

public class MessageService {

	Map<Long,Message> messages  =  DatabaseClass.getAllMessages();
	
	public MessageService(){
		messages.put(1L, new Message(1L,"Hello World!","Teja"));
		messages.put(2L, new Message(2L,"Hello World!","Deepak"));
	}
	
	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(long id){
		Message message = messages.get(id);
		if(null==message){
			throw new DataNotFoundException("No data found for the message id : "+id);
		}
		return message;
	}
	
	public List<Message> getAllMessagesForYear(int year){
		List<Message> yearList = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for(Message mes : messages.values()){
			cal.setTime(mes.getCreated());
			if(cal.get(Calendar.YEAR)==year){
				yearList.add(mes);
			}
		}
		return yearList;
	}
	
	public List<Message> getMessagesByPagination(int start, int size){
		List<Message> list = new ArrayList<Message>();
		return list.subList(start, start+size);
	}
	
	public Message addMessage(Message message){
		message.setId(messages.size()+1);
		messages.put(message.getId(),message);
		return message;
	}
	
	public Message updateMessage(Message message){
		if(message.getId() <= 0){
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id){
		return messages.remove(id);
	}
}

