package com.synchrony.rest.api.service.impl;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import com.synchrony.rest.api.exception.FileStorageException;
import com.synchrony.rest.api.exception.ImgurConnectionException;
import com.synchrony.rest.api.exception.UnexpectedException;
import com.synchrony.rest.api.util.ApiUtil;
import com.synchrony.rest.api.util.AppConstants;

@Service
public class FileStorageService {

	private final Path fileStorageLocation;
	private final Logger logger = LoggerFactory.getLogger(FileStorageService.class);

	@Autowired
	public FileStorageService() {

		this.fileStorageLocation = Paths.get(AppConstants.IMAGE_DOWNLOAD_PATH).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	public String storeFile(HttpURLConnection conn, String targetLocation) {

		try {
			try (BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
					FileOutputStream fileOutputStream = new FileOutputStream(targetLocation)) {
				byte dataBuffer[] = new byte[1024];
				int bytesRead;
				while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
					fileOutputStream.write(dataBuffer, 0, bytesRead);
				}
				in.close();
				return "Downloaded file in " + targetLocation;
			} catch (ResourceAccessException hostException) {
				throw new ImgurConnectionException("Unable to connect with Server");

			} catch (HttpClientErrorException | IOException e) {
				throw new ImgurConnectionException("Unable to connect with Server", e);
			} finally {
				try {
					if (conn.getResponseCode() != 200) {
						conn.getResponseMessage();
					}
				} catch (IOException e) {
					throw new ImgurConnectionException("Found IOException");
				}
			}
		} catch (Exception ex) {
			throw new FileStorageException("Could not store file " + targetLocation + ". Please try again!", ex);
		}
	}

	public HttpURLConnection loadFileAsResource(String linkUrl) {
		HttpURLConnection con = null;

		try {

			URL url = new URL(linkUrl);
			con = (HttpURLConnection) url.openConnection();
			if (con.getErrorStream() != null) {
				throw new ImgurConnectionException("Connection error to Imgur " + linkUrl);
			} else {
				return con;
			}
		} catch (MalformedURLException ex) {
			throw new ImgurConnectionException("MalformedURLException " + linkUrl);
		} catch (IOException e) {
			throw new ImgurConnectionException("IOException in imgur url connection");
		}
	}

	public String downloadFile(String linkUrlFormatted) {
		logger.info(" downloadFile from  " + linkUrlFormatted);
		try {
			HttpURLConnection con = loadFileAsResource(linkUrlFormatted);

			if (con != null) {
				// check target location
				String targetLocation = ApiUtil.formatFileTargetPath(linkUrlFormatted);
				if (targetLocation != null)
					return storeFile(con, targetLocation);
			}
		} catch (Exception ex) {
			throw new UnexpectedException("Connected to Imgur! Error in Download from " + linkUrlFormatted);
		}
		return "Connected to Imgur! Error in Download from " + linkUrlFormatted;
	}

}