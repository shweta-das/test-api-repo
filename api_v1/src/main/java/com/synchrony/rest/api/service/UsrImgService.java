package com.synchrony.rest.api.service;

import com.synchrony.rest.api.model.UserImg;

public interface UsrImgService {

	public UserImg connectImgur(String username);
	
	public void sendUserImgInKafka(UserImg dto);

}
