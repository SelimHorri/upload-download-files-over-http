package com.selimhorri.pack.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.selimhorri.pack.dto.FileHandlerDto;

public interface FileHandlerService {
	
	List<String> upload(final List<MultipartFile> multipartFiles);
	FileHandlerDto download(final String filename);
	
}
