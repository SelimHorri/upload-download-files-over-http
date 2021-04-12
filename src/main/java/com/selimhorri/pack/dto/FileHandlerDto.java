package com.selimhorri.pack.dto;

import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

public class FileHandlerDto {
	
	private Path filePath;
	private Resource resource;
	private HttpHeaders headers;
	
	public FileHandlerDto() {
		
	}
	
	public FileHandlerDto(Path filePath, Resource resource, HttpHeaders headers) {
		this.filePath = filePath;
		this.resource = resource;
		this.headers = headers;
	}
	
	public Path getFilePath() {
		return filePath;
	}
	
	public void setFilePath(Path filePath) {
		this.filePath = filePath;
	}
	
	public Resource getResource() {
		return resource;
	}
	
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	public HttpHeaders getHeaders() {
		return headers;
	}
	
	public void setHeaders(HttpHeaders headers) {
		this.headers = headers;
	}
	
	
	
}




