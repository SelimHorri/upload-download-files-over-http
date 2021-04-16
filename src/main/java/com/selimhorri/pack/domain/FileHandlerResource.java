package com.selimhorri.pack.domain;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.selimhorri.pack.dto.FileHandlerDto;
import com.selimhorri.pack.service.FileHandlerService;

@RestController
@RequestMapping("/app/api/files")
public class FileHandlerResource {
	
	private final FileHandlerService fileHandlerService;
	
	@Autowired
	public FileHandlerResource(final FileHandlerService fileHandlerService) {
		this.fileHandlerService = fileHandlerService;
	}
	
	@PostMapping(value = { "/upload" })
	public ResponseEntity<List<String>> upload(@RequestParam("files") final List<MultipartFile> multipartFiles) {
		return ResponseEntity.ok().body(this.fileHandlerService.upload(multipartFiles));
	}
	
	@GetMapping(value = {"/download/{filename}"})
	public ResponseEntity<Resource> download(@PathVariable("filename") final String filename) throws IOException {
		final FileHandlerDto fileHandlerDto = this.fileHandlerService.download(filename);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(fileHandlerDto.getFilePath()))).headers(fileHandlerDto.getHeaders()).body(fileHandlerDto.getResource());
	}
	
	
	
}







