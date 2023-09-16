package com.synchrony.rest.api.util;

public class AppConstants {

	public static final String BEARER = "Bearer ";
	public static final String GROUP = "UserImageGroup";
	
	public static final String IMAGE_DOWNLOAD_PATH = "/Users/images/";
	
	public static final String IMGUR_ACCESS_TOKEN_URL = "https://api.imgur.com/oauth2/token";
	public static final String IMGUR_AUTH_URL = "https://api.imgur.com/oauth2/authorize";

	public static final String IMGUR_CLIENT_ID = "e135daae5f6c21e";
	public static final String IMGUR_CLIENT_NAME = "shwet1";
	public static final String IMGUR_CLIENT_SECRET = "c1b67e6f2ea4b48468280b947c21c0d5a9de68ab";
	
	public static final String IMGUR_URL_DOWNLOAD = "https://api.imgur.com/3/account/" + IMGUR_CLIENT_NAME + "/image/";
	public static final String IMGUR_URL_UPLOAD = "https://api.imgur.com/3/upload";
	
	public static final String JSON_LINK = "link";
	
	public static final String TOPIC_DTO = "UserImageDetailsTopic";
	public static final String TOPIC_ADMIN = "kafkaAdminTopic";
	
}
