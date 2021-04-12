package com.selimhorri.pack.util;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class FileHandlerUtilImpl implements FileHandlerUtil {
	
	@Override
	public Path getByDirectoryAndFilename(final String DIRECTORY, final String filename) {
		return Paths.get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
	}
	
	@Override
	public Resource getByPath(final Path filePath) {
		
		Resource resource = null;
		
		try {
			resource = new UrlResource(filePath.toUri());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return resource;
	}
	
	@Override
	public HttpHeaders getByResource(final Resource resource) {
		
		final HttpHeaders headers = new HttpHeaders();
		headers.add("File-Name", resource.getFilename());
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
		
		return headers;
	}
	
	
	
}





