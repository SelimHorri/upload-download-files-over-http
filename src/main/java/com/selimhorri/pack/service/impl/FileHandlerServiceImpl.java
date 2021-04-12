package com.selimhorri.pack.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.selimhorri.pack.service.FileHandlerService;

@Service
public class FileHandlerServiceImpl implements FileHandlerService {
	
	public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads";
	
	@Override
	public List<String> upload(final List<MultipartFile> multipartFiles) {
		
		final List<String> filenames = new ArrayList<>();
		
		for (MultipartFile multipartFile : multipartFiles) {
			String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			Path fileStorage = Paths.get(DIRECTORY, filename).toAbsolutePath().normalize();
			try {
				Files.copy(multipartFile.getInputStream(), fileStorage, StandardCopyOption.REPLACE_EXISTING);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			filenames.add(filename);
		}
		
		return filenames;
	}
	
	
	
}








