package com.sterp.multitenant.tenant.uploadutility.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.sterp.multitenant.tenant.dto.DocumentRequestDTO;
import com.sterp.multitenant.tenant.uploadutility.dto.IfDefaultLoggedInData;

public interface DocumentUploadService {
	public void uploadDocuments(List<DocumentRequestDTO> documentRequestDTO, MultipartFile[] files,
			IfDefaultLoggedInData defaultLoggedInData, long documentId, String documentName,
			HttpServletRequest request);

	public void fetchDocuments(IfDefaultLoggedInData defaultLoggedInData, HttpServletRequest request);

//	public DocumentFolder documentFolderByName(String documentFolder);
}
