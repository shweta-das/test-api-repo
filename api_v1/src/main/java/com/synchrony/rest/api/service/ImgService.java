package com.synchrony.rest.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import okhttp3.Response;

@Service
public interface ImgService{

	String uploadImage(MultipartFile file);
	String deleteImage(String imgId);
	List<MultipartFile> getImages();
	String getImage(String token, String url);
	
}
