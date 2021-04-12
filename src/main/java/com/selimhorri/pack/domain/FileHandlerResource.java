package com.selimhorri.pack.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.selimhorri.pack.service.FileHandlerService;

@RestController
@RequestMapping("/app/api/files")
public class FileHandlerResource {
	
	private final FileHandlerService fileHandlerService;
	
	@Autowired
	public FileHandlerResource(FileHandlerService fileHandlerService) {
		this.fileHandlerService = fileHandlerService;
	}
	
	@PostMapping(value = { "/upload" })
	public ResponseEntity<List<String>> upload(@RequestParam("files") final List<MultipartFile> multipartFiles) {
		return ResponseEntity.ok().body(this.fileHandlerService.upload(multipartFiles));
	}
	
	
	
}







