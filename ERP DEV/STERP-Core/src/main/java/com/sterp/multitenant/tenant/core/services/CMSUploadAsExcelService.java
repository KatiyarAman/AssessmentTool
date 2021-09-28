package com.sterp.multitenant.tenant.core.services;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface CMSUploadAsExcelService {
	public Map<String, Object> uploadCMSAsExcel(MultipartFile multipartFile) throws IOException;
}
