package com.teja.jetdevelops.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.teja.jetdevelops.messenger.database.DatabaseClass;
import com.teja.jetdevelops.messenger.model.Profile;

public class ProfileService {

	Map<String,Profile> profiles = DatabaseClass.getAllProfiles();
	
	public ProfileService(){
		profiles.put("teja463", new Profile(1L, "teja463", "Teja", "Ponnuru"));
		profiles.put("pramodch", new Profile(2L,"pramodch", "Pramod", "Cheekurthy"));
	}
	
	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String name){
		return profiles.get(name);
	}
	
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfileName(), profile);
		return profiles.get(profile.getProfileName());
	}
	
	public Profile updateProfile(Profile profile){
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public void removeProfile(String name){
		profiles.remove(name);
	}
}
