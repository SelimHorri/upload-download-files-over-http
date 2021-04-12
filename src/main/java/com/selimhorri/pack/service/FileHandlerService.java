package com.selimhorri.pack.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileHandlerService {
	
	List<String> upload(final List<MultipartFile> multipartFiles);
	
}
