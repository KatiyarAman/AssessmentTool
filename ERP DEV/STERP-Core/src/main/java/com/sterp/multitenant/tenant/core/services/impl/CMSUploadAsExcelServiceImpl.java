package com.sterp.multitenant.tenant.core.services.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.core.services.CMSUploadAsExcelService;

@Service
public class CMSUploadAsExcelServiceImpl implements CMSUploadAsExcelService {

	@Autowired
	AppService appService;

	@Override
	public Map<String, Object> uploadCMSAsExcel(MultipartFile multipartFile) throws IOException {
		appService.callReadFile(multipartFile, multipartFile.getOriginalFilename());
		return null;
	}

}
