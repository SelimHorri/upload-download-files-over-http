package com.selimhorri.pack.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.selimhorri.pack.dto.FileHandlerDto;
import com.selimhorri.pack.service.FileHandlerService;
import com.selimhorri.pack.util.FileHandlerUtil;

@Service
public class FileHandlerServiceImpl implements FileHandlerService {
	
	private final FileHandlerUtil fileHandlerUtil;
	public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads";
	
	@Autowired
	public FileHandlerServiceImpl(final FileHandlerUtil fileHandlerUtil) {
		this.fileHandlerUtil = fileHandlerUtil;
	}
	
	@Override
	public List<String> upload(final List<MultipartFile> multipartFiles) {
		
		final List<String> filenames = new ArrayList<>();
		
		for (MultipartFile multipartFile : multipartFiles) {
			final String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			final Path fileStorage = Paths.get(DIRECTORY, filename).toAbsolutePath().normalize();
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
	
	@Override
	public FileHandlerDto download(final String filename) {
		
		final FileHandlerDto fileHandlerDto = new FileHandlerDto();
		
		final Path filePath = this.fileHandlerUtil.getByDirectoryAndFilename(DIRECTORY, filename);
		final Resource resource = this.fileHandlerUtil.getByPath(filePath);
		final HttpHeaders headers = this.fileHandlerUtil.getByResource(resource);
		fileHandlerDto.setFilePath(filePath);
		fileHandlerDto.setResource(resource);
		fileHandlerDto.setHeaders(headers);
		
		return fileHandlerDto;
	}
	
	
	
}








