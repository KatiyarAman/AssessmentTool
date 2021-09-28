package com.sterp.multitenant.tenant.core.services;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
	public <T> void storeSingleFile(MultipartFile multipartFile, Class<T> t);

	public <T> void storeFromDatabaseAsBlob(InputStream fin, String fileName, String destination);
}
