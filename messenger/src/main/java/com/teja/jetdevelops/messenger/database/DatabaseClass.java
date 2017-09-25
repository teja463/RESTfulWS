package com.teja.jetdevelops.messenger.database;

import java.util.HashMap;
import java.util.Map;

import com.teja.jetdevelops.messenger.model.Message;
import com.teja.jetdevelops.messenger.model.Profile;

public class DatabaseClass {

	private static Map<Long, Message> messages = new HashMap<Long,Message>();
	private static Map<String, Profile> profiles = new HashMap<String, Profile>();
	
	public static Map<Long, Message> getAllMessages(){
		return messages;
	}
	
	public static Map<String, Profile> getAllProfiles(){
		return profiles;
	}
}
