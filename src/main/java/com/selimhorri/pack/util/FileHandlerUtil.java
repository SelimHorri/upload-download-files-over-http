package com.selimhorri.pack.util;

import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

public interface FileHandlerUtil {
	
	Path getByDirectoryAndFilename(final String DIRECTORY, final String filename);
	Resource getByPath(final Path filePath);
	HttpHeaders getByResource(final Resource resource);
	
}
