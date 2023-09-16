package com.synchrony.rest.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.synchrony.rest.api.response.ApiResponse;
import com.synchrony.rest.api.response.ServiceResponse;
import com.synchrony.rest.api.service.ImgService;
import com.synchrony.rest.api.util.AppConstants;

@RestController
@RequestMapping(value = "/img", produces = MediaType.APPLICATION_JSON_VALUE)
public class ImgController {

//	private final Logger logger = LoggerFactory.getLogger(ImgController.class);

	@Autowired
	private ImgService imgService;
	
	@PostMapping("/upload")
	public ResponseEntity<ServiceResponse> fileUpload(@RequestParam("imgFile") MultipartFile imgFile) throws Exception{
		return ApiResponse.getResponse(this.imgService.uploadImage(imgFile));	
	}

	@DeleteMapping("/images/{imgid}")
	public ResponseEntity<ServiceResponse> deleteImage(@RequestParam("imgid") String imgId) {
		return ApiResponse.getResponse(this.imgService.deleteImage(imgId));	
	}

	@GetMapping("/images")
	public ResponseEntity<List<MultipartFile>> getImages() {
		return ResponseEntity.ok().body(this.imgService.getImages());
	}

	@GetMapping("/images/{imgName}")
	public ResponseEntity<ServiceResponse> getImage(@RequestParam("token") String token, @PathVariable String imgName) {
		String url = AppConstants.IMGUR_URL_DOWNLOAD + imgName;
		return ApiResponse.getResponse(this.imgService.getImage(AppConstants.BEARER+token, url));	
	}
}
