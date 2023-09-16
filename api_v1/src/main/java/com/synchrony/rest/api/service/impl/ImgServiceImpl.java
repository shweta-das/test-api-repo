package com.synchrony.rest.api.service.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import com.synchrony.rest.api.exception.UnexpectedException;
import com.synchrony.rest.api.service.ImgService;
import com.synchrony.rest.api.util.ApiRestConnect;
import com.synchrony.rest.api.util.ApiUtil;
import com.synchrony.rest.api.util.AppConstants;

@Service
public class ImgServiceImpl implements ImgService {

	private final Logger logger = LoggerFactory.getLogger(ImgServiceImpl.class);

	@Autowired
	WebClient webClient;

	@Override
	public String uploadImage(MultipartFile file) {
		logger.info("uploadImage() start!");
		String result = "Upload event failed.";
		try {
			String fileName = file.getOriginalFilename();
			String filePath = AppConstants.IMAGE_DOWNLOAD_PATH + File.separator + fileName;

			File f = new File(AppConstants.IMAGE_DOWNLOAD_PATH);
			if (!f.exists()) {
				f.mkdir();
			}
			Files.copy(file.getInputStream(), Paths.get(filePath));
			result = "Image file " + fileName + " is successfully uploaded.";
		} catch (Exception ex) {
			throw new UnexpectedException("Exception in uploadImage method.");
		}
		return result;
	}

	@Override
	public String deleteImage(String imgId) {
		return null;
	}

	@Override
	public List<MultipartFile> getImages() {
		return null;
	}

	@Override
	public String getImage(String token, String url) {
		logger.info( " File download in getImage() from " +url);
		String link = ApiRestConnect.connectImgurRestTemplate(token, url).get();		
		logger.info(" link found in connectImgurRestTemplate() :: " + link);
		return new FileStorageService().downloadFile(ApiUtil.removeBrackets(link));
	}
	

	

}
