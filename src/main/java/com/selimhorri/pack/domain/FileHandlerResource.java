package com.selimhorri.pack.domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/app/api/files")
public class FileHandlerResource {
	
	public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads";
	
	@PostMapping(value = { "/upload" })
	public ResponseEntity<List<String>> upload(@RequestParam("files") final List<MultipartFile> multipartFiles) throws IOException {
		
		final List<String> filenames = new ArrayList<>();
		
		for (MultipartFile multipartFile : multipartFiles) {
			String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			Path fileStorage = Paths.get(DIRECTORY, filename).toAbsolutePath().normalize();
			Files.copy(multipartFile.getInputStream(), fileStorage, StandardCopyOption.REPLACE_EXISTING);
			filenames.add(filename);
		}
		
		return ResponseEntity.ok().body(filenames);
	}
	
	
	
}







