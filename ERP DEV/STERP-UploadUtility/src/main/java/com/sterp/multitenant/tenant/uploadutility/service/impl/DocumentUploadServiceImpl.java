package com.sterp.multitenant.tenant.uploadutility.service.impl;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sterp.multitenant.tenant.dto.DocumentRequestDTO;
import com.sterp.multitenant.tenant.exceptionhandler.CustomException;
import com.sterp.multitenant.tenant.modules.entity.Modules;
import com.sterp.multitenant.tenant.service.ModuleServiceMain;
import com.sterp.multitenant.tenant.uploadutility.dto.IfDefaultLoggedInData;
import com.sterp.multitenant.tenant.uploadutility.entity.DocumentContent;
import com.sterp.multitenant.tenant.uploadutility.entity.DocumentFolder;
import com.sterp.multitenant.tenant.uploadutility.entity.DocumentVersion;
import com.sterp.multitenant.tenant.uploadutility.entity.Documents;
import com.sterp.multitenant.tenant.uploadutility.repository.DocumentContentRepository;
import com.sterp.multitenant.tenant.uploadutility.repository.DocumentFolderRepository;
import com.sterp.multitenant.tenant.uploadutility.repository.DocumentVersionRepository;
import com.sterp.multitenant.tenant.uploadutility.repository.DocumentsRepository;
import com.sterp.multitenant.tenant.uploadutility.service.DocumentUploadService;
import com.sterp.multitenant.tenant.uploadutility.utils.FileHashingMD;

@Service
public class DocumentUploadServiceImpl implements DocumentUploadService {

	@Autowired
	ModuleServiceMain moduleService;

	@Autowired
	DocumentFolderRepository documentFolderRepository;

	@Autowired
	DocumentVersionRepository documentVersionRepository;

	@Autowired
	DocumentsRepository documentsRepository;

	@Autowired
	FileStorageUploadServiceImpl fileStorageUploadServiceImpl;

	@Autowired
	DocumentContentRepository documentContentRepository;

	@Autowired
	ServletContext ctx;

	@Override
	public void fetchDocuments(IfDefaultLoggedInData defaultLoggedInData, HttpServletRequest request) {
		// TODO Auto-generated method stub
		long moduleId = Long.parseLong(request.getHeader("moduleId"));
		String moduleName = moduleService.getModuleById(moduleId, false).getTitle();

		StringBuilder pathPrefix = new StringBuilder();
		if (moduleName.equalsIgnoreCase("Company")) {
			// pathPrefix.
		} else if (moduleName.equalsIgnoreCase("Branch")) {

		} else {

		}

	}

	@Override
	public void uploadDocuments(List<DocumentRequestDTO> documentRequestDTOList, MultipartFile[] files,
			IfDefaultLoggedInData defaultLoggedInData, long refDocId, String name, HttpServletRequest request) {

		long moduleId = Long.parseLong(request.getHeader("moduleId"));
		String moduleName = moduleService.getModuleById(moduleId, false).getTitle();
		DocumentFolder saveDocumentFolder = new DocumentFolder();
		StringBuffer destination = new StringBuffer();
		if (moduleName.equalsIgnoreCase("Company")) {
			// Check if Folder already created with the existing company name.
			if (!this.documentFolderByName(name, moduleId)) {

				// Create the folder - Database level
				saveDocumentFolder = this.saveDocumentFolder(moduleId, name, defaultLoggedInData, null);
				// Start of iteration of DocumentRequestDto and storing it.
				saveDocumentAndTheirVersion(refDocId,documentRequestDTOList,defaultLoggedInData,destination,moduleId,name,saveDocumentFolder.getId(),files);
			} else {
						//version 2 pending......................
			}
		} else if (moduleName.equalsIgnoreCase("Branch")) {
			Modules parentModule = moduleService.getModuleByTitle("Company", false);

			if (!this.documentFolderByName(defaultLoggedInData.getCompanyName(), parentModule.getId())) {
				saveDocumentFolder = this.saveDocumentFolder(parentModule.getId(), name, defaultLoggedInData, null);
			} else {
			    saveDocumentFolder=this.documentFolderRepository.findByParentFolderIdAndStatusAndModuleId(null,2L,parentModule.getId()).get();
				//saveDocumentFolder = this.documentFolderRepository.findById(33L).get();
			}
			DocumentFolder saveChildDocumentFolder = new DocumentFolder();
			if (!this.documentFolderByName(name, parentModule.getId())) {
				saveChildDocumentFolder = this.saveDocumentFolder(moduleId, name, defaultLoggedInData,
						saveDocumentFolder.getId());

				MessageDigest md5Digest = null;
				try {
					md5Digest = MessageDigest.getInstance("MD5");

					List<Documents> documentToBeStoredList = new ArrayList<Documents>();

//					List<DocumentVersion> uploadDocumentVersionResponse = new ArrayList<DocumentVersion>();

					for (int i = 0; i < documentRequestDTOList.size(); i++) {
						destination.delete(0, destination.length());
						destination.append(defaultLoggedInData.getCompanyName()+"/" + name + "/");
						DocumentFolder saveDocumentFolderChild = new DocumentFolder();

						DocumentRequestDTO documentRequestDTO = documentRequestDTOList.get(i);

						if (!this.documentFolderByName(documentRequestDTO.getTypeIdtitle(), moduleId)) {
							saveDocumentFolderChild = this.saveDocumentFolder(moduleId,
									documentRequestDTO.getTypeIdtitle(), defaultLoggedInData,
									saveChildDocumentFolder.getId());
							destination.append(saveDocumentFolderChild.getFolderName() + "/");
						} else {

						}

						Documents documents = new Documents();
						documents.setDocumentName(documentRequestDTO.getDocumentName());
						documents.setDocumentFolderId(saveDocumentFolderChild.getId());
						documents.setTypeId(Long.parseLong(documentRequestDTO.getTypeId()));
						documents.setRefDoc(refDocId);
						documents.setStatus(2L);
						documents.setShownInModule(true);
						documents.setCreatedBy(defaultLoggedInData.getCreatedById());
						documents.setModifiedBy(defaultLoggedInData.getCreatedById());
						documents.setCreatedDate(new Date(System.currentTimeMillis()));
						documents.setModifiedDate(new Date(System.currentTimeMillis()));
						documents.setBranchId(defaultLoggedInData.getBranchId());
						documents.setCompanyId(defaultLoggedInData.getCompanyId());

						int[] fileArray = documentRequestDTO.getFiles();

						Set<DocumentVersion> documentVersionToStoreList = new HashSet<DocumentVersion>();
						for (int j = 0; j < fileArray.length; j++) {
							MultipartFile fileToUpload = files[fileArray[j]];
							String fileHash = FileHashingMD.getFileChecksum(md5Digest, fileToUpload.getBytes());
							DocumentVersion documentVersion = this.documentVersionRepository
									.findByHashKeyAndStatus(fileHash, 2L);

							if (documentVersion != null) {
								// same hashkey
							} else {
								// different hashkey
								// check if different or same fileName
								if (this.documentVersionRepository.findByFileNameAndDocumentIdAndStatus(
										fileToUpload.getOriginalFilename(), saveDocumentFolderChild.getId(),
										2L) != null) {
									// same fileName

								} else {
									// different fileName
									// check if file is being uploaded under fixed documentType
									// The current logic is considered as the document is not a fixed Document Type
									DocumentVersion documentVersionToStore = new DocumentVersion();
									try {
										documentVersionToStore = this.fileStorageUploadServiceImpl.storeTemplateSingle(
												fileToUpload, documentVersionToStore, destination, 1L);

										documentVersionToStore.setCompanyId(defaultLoggedInData.getCompanyId());
										documentVersionToStore.setBranchId(defaultLoggedInData.getBranchId());
									} catch (OutOfMemoryError e) {
										throw new CustomException("Maximum Storage Already Consumes!");
									} catch (Exception e) {
										throw new CustomException("please Increase Db Max_allowed_Packet!");
									}
									documentVersionToStoreList.add(documentVersionToStore);
								}
							}
						}
						documents.setDocumentVersions(documentVersionToStoreList);
						documentToBeStoredList.add(documents);
						// uploadDocumentVersionResponse.add(documentVersionToStore);
					}

					for (Documents uploadDocument : documentToBeStoredList) {
						uploadDocument = uploadDocument.getId() == null
								? this.documentsRepository.saveAndFlush(uploadDocument)
								: uploadDocument;
						for (DocumentVersion uploadDocumentVersion : uploadDocument.getDocumentVersions()) {
							if (uploadDocumentVersion.getDocumentContent() != null) {
								DocumentContent content = uploadDocumentVersion.getDocumentContent();
								uploadDocumentVersion.setDocumentId(uploadDocument.getId());
								uploadDocumentVersion = this.documentVersionRepository.save(uploadDocumentVersion);
								long uploadDocumentVersionId = uploadDocumentVersion.getId();
								content.setDocumentVersionId(uploadDocumentVersionId);
								this.documentContentRepository.saveAndFlush(content);
							}
						}
					}
					System.out.println("Test..........");
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} else {

		}

	}

	private boolean documentFolderByName(String documentFolderName, Long moduleId) {
		System.out.println(documentFolderRepository.findAll());
		Optional<DocumentFolder> optional = this.documentFolderRepository
				.findByFolderNameAndStatusAndModuleId(documentFolderName, 2L, moduleId);
		if (optional.isPresent()) {
			return true;
		}
		return false;
	}

	public DocumentFolder saveDocumentFolder(long moduleId, String name, IfDefaultLoggedInData defaultLoggedInData,
			Long parentId) {
		DocumentFolder saveDocumentFolder = new DocumentFolder();
		saveDocumentFolder.setFolderName(name);
		saveDocumentFolder.setModuleId(moduleId);
		saveDocumentFolder.setParentFolderId(parentId);
		saveDocumentFolder.setCompanyId(defaultLoggedInData.getCompanyId());
		saveDocumentFolder.setBranchId(defaultLoggedInData.getBranchId());
		saveDocumentFolder.setCreatedBy(defaultLoggedInData.getCreatedById());
		saveDocumentFolder.setModifiedBy(defaultLoggedInData.getCreatedById());
		saveDocumentFolder.setCreatedDate(new Date(System.currentTimeMillis()));
		saveDocumentFolder.setModifiedDate(new Date(System.currentTimeMillis()));
		saveDocumentFolder.setStatus(2L);
		saveDocumentFolder = this.documentFolderRepository.save(saveDocumentFolder);

		return saveDocumentFolder;
	}
	
	
	public void saveDocumentAndTheirVersion(Long refDocId,List<DocumentRequestDTO> documentRequestDTOList,IfDefaultLoggedInData defaultLoggedInData,StringBuffer destination,Long moduleId,String name,Long documentId,MultipartFile[] files)
	{
		MessageDigest md5Digest = null;
		try {
			md5Digest = MessageDigest.getInstance("MD5");

			List<Documents> documentToBeStoredList = new ArrayList<Documents>();

//			List<DocumentVersion> uploadDocumentVersionResponse = new ArrayList<DocumentVersion>();

			for (int i = 0; i < documentRequestDTOList.size(); i++) {
				destination.delete(0, destination.length());
				destination.append(name + "/");
				DocumentFolder saveDocumentFolderChild = new DocumentFolder();

				DocumentRequestDTO documentRequestDTO = documentRequestDTOList.get(i);

				if (!this.documentFolderByName(documentRequestDTO.getTypeIdtitle(), moduleId)) {
					saveDocumentFolderChild = this.saveDocumentFolder(moduleId,
							documentRequestDTO.getTypeIdtitle(), defaultLoggedInData,
							documentId);
					destination.append(saveDocumentFolderChild.getFolderName() + "/");
				} else {

				}

				Documents documents = new Documents();
				documents.setDocumentName(documentRequestDTO.getDocumentName());
				documents.setDocumentFolderId(saveDocumentFolderChild.getId());
				documents.setTypeId(Long.parseLong(documentRequestDTO.getTypeId()));
				documents.setRefDoc(refDocId);
				documents.setStatus(2L);
				documents.setShownInModule(true);
				documents.setCreatedBy(defaultLoggedInData.getCreatedById());
				documents.setModifiedBy(defaultLoggedInData.getCreatedById());
				documents.setCreatedDate(new Date(System.currentTimeMillis()));
				documents.setModifiedDate(new Date(System.currentTimeMillis()));
				documents.setBranchId(defaultLoggedInData.getBranchId());
				documents.setCompanyId(defaultLoggedInData.getCompanyId());

				int[] fileArray = documentRequestDTO.getFiles();

				Set<DocumentVersion> documentVersionToStoreList = new HashSet<DocumentVersion>();
				for (int j = 0; j < fileArray.length; j++) {
					MultipartFile fileToUpload = files[fileArray[j]];
					String fileHash = FileHashingMD.getFileChecksum(md5Digest, fileToUpload.getBytes());
					DocumentVersion documentVersion = this.documentVersionRepository
							.findByHashKeyAndStatus(fileHash, 2L);

					if (documentVersion != null) {
						// same hashkey
					} else {
						// different hashkey
						// check if different or same fileName
						if (this.documentVersionRepository.findByFileNameAndDocumentIdAndStatus(
								fileToUpload.getOriginalFilename(), saveDocumentFolderChild.getId(),
								2L) != null) {
							// same fileName

						} else {
							// different fileName
							// check if file is being uploaded under fixed documentType
							// The current logic is considered as the document is not a fixed Document Type
							DocumentVersion documentVersionToStore = new DocumentVersion();
							try {
								documentVersionToStore = this.fileStorageUploadServiceImpl.storeTemplateSingle(
										fileToUpload, documentVersionToStore, destination, 1L);

								documentVersionToStore.setCompanyId(defaultLoggedInData.getCompanyId());
								documentVersionToStore.setBranchId(defaultLoggedInData.getBranchId());
							} catch (OutOfMemoryError e) {
								throw new CustomException("Maximum Storage Already Consumes!");
							} catch (Exception e) {
								throw new CustomException("please Increase Db Max_allowed_Packet!");
							}
							documentVersionToStoreList.add(documentVersionToStore);
						}
					}
				}
				documents.setDocumentVersions(documentVersionToStoreList);
				documentToBeStoredList.add(documents);
				// uploadDocumentVersionResponse.add(documentVersionToStore);
			}

			for (Documents uploadDocument : documentToBeStoredList) {
				uploadDocument = uploadDocument.getId() == null
						? this.documentsRepository.saveAndFlush(uploadDocument)
						: uploadDocument;
				for (DocumentVersion uploadDocumentVersion : uploadDocument.getDocumentVersions()) {
					if (uploadDocumentVersion.getDocumentContent() != null) {
						DocumentContent content = uploadDocumentVersion.getDocumentContent();
						uploadDocumentVersion.setDocumentId(uploadDocument.getId());
						uploadDocumentVersion = this.documentVersionRepository.save(uploadDocumentVersion);
						long uploadDocumentVersionId = uploadDocumentVersion.getId();
						content.setDocumentVersionId(uploadDocumentVersionId);
						this.documentContentRepository.saveAndFlush(content);
					}
				}
			}
			System.out.println("Test..........");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getFilePath(String pathPrefix, DocumentVersion documentVersion, Documents uploadedDocument,
			DocumentFolder parentFolder) {

		Blob content = this.documentContentRepository.findByDocumentVersionId(documentVersion.getId())
				.getDocumentContent();

		StringBuilder destination = new StringBuilder(pathPrefix + "/");

		DocumentFolder parentFolderTemp = parentFolder;

		if (parentFolderTemp.getParentFolderId() != null) {
			parentFolderTemp = this.recurseToParent(parentFolderTemp);
		}

		this.recursiveSubDirectories(parentFolderTemp, "rename", destination, uploadedDocument.getDocumentFolderId());
		try {
			String fileName = documentVersion.getFileName();
			MessageDigest md5Digest = MessageDigest.getInstance("MD5");
			String filePath = ctx.getRealPath(destination.toString() + "/" + fileName);
			System.out.println("Path Location : " + filePath);
			File file = new File(filePath);
			if (file.exists()) {
				String fileHash = FileHashingMD.getFileChecksum(md5Digest,
						java.nio.file.Files.readAllBytes(file.toPath()));
				if (fileHash.equalsIgnoreCase(documentVersion.getHashKey())) {
					return filePath;
				} else {
					this.fileStorageUploadServiceImpl.storeFromDatabaseAsBlob(content.getBinaryStream(),
							documentVersion.getFileName(), destination.toString());
					File newSaved = new File(filePath);
					if (newSaved.exists()) {
						return filePath;
					} else {
						throw new CustomException(
								"Oops! Something went wrong. Please try again later. We are working on it.");
					}
				}
			} else {
				this.fileStorageUploadServiceImpl.storeFromDatabaseAsBlob(content.getBinaryStream(),
						documentVersion.getFileName(), destination.toString());
				File newSaved = new File(filePath);
				if (newSaved.exists()) {
					return filePath;
				} else {
					throw new CustomException(
							"Oops! Something went wrong. Please try again later. We are working on it.");
				}
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Recursive call to traverse to the root or parent folder this block check
	 * if @IRBDocumentFolder.ParentDocumentFolderId is 0 which indicates to be the
	 * root folder and is returned backed to the calling method
	 */
	public DocumentFolder recurseToParent(DocumentFolder parentFolder) {
		DocumentFolder parentFoldertemp = this.documentFolderRepository.findById(parentFolder.getParentFolderId())
				.get();
		if (parentFoldertemp != null && parentFoldertemp.getParentFolderId() == 0) {
			return parentFoldertemp;
		} else {
			return recurseToParent(parentFoldertemp);
		}
	}

	/**
	 * recursive call to create a directory list containing all the sub-directories
	 * information until child sub-directory is not found, and sub-directories list
	 * chain is returned to the calling method.
	 */
	private List<DocumentFolder> recursiveSubDirectories(DocumentFolder dd, String type,
			StringBuilder destinationReverse, Long selectedFolderId) {
		List<DocumentFolder> subDirectories = this.documentFolderRepository.findByParentFolderIdAndStatus(dd.getId(),
				2L);
		if (type.equalsIgnoreCase("rename")) {
			destinationReverse.append(dd.getFolderName() + "/");
			if (selectedFolderId == dd.getId()) {
				return subDirectories;
			}
		}
		if (subDirectories.size() != 0) {
			for (DocumentFolder documentFolder : subDirectories) {
				documentFolder.setSubDirectoriesList(
						this.recursiveSubDirectories(documentFolder, type, destinationReverse, selectedFolderId));
			}
		}
		return subDirectories;
	}

}
