package com.synchrony.rest.api.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
public class UserImg{
		
	
	private String userName;
	private List<String> imgList;

	public UserImg(String userName, List<String> imgList) {
		this.userName = userName;
		this.imgList = imgList;
	}
	
	@Override
	public String toString() {
		String fileNames = "";
		for(String s:imgList){
			fileNames += "\'" + s + "\'";
		}
		return "User Image Detail{" + "username=" + userName + ", images=" + fileNames + '}';
	}
}