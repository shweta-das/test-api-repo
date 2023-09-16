package com.synchrony.rest.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.synchrony.rest.api.model.UserImg;
import com.synchrony.rest.api.service.UsrImgService;

@Service
public class UsrImgServiceImpl implements UsrImgService{
	
	 
    private Map<String, String> images = new HashMap<>();
	private final Logger logger = LoggerFactory.getLogger(ImgServiceImpl.class);

    @Override
	public UserImg connectImgur(String username) {
    	Optional<List<String>> images = null; //Call service to connect Imgur service 
    	//get images and details;
    	  images.ifPresent(img -> {
              UserImg userImages = new UserImg(username, images.get());
              //fill and extract images with Image file name and file
              //this.UserImg(userName, ImgFileName);
              UserImg dto=null;
              sendUserImgInKafka(dto);
          });
    	  //return images to user
    	  return null;
    }

    @Override
    public void sendUserImgInKafka(UserImg dto) {

        //publish
    }

}
