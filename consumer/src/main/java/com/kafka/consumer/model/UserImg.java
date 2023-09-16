package com.kafka.consumer.model;

import java.util.List;

import lombok.Data;

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