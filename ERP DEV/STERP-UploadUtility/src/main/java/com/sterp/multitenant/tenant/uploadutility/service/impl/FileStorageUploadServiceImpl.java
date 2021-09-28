package com.sterp.multitenant.tenant.uploadutility.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sterp.multitenant.tenant.config.FileStorageProperties;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.exceptionhandler.FileStorageException;
import com.sterp.multitenant.tenant.uploadutility.entity.DocumentContent;
import com.sterp.multitenant.tenant.uploadutility.entity.DocumentVersion;
import com.sterp.multitenant.tenant.uploadutility.utils.FileHashingMD;

@Service
public class FileStorageUploadServiceImpl {
	private final Path fileStorageLocation;

	@Autowired
	ServletContext ctx;

	@Autowired
	AppService appService;

	@Autowired
	public FileStorageUploadServiceImpl(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	public DocumentVersion storeTemplateSingle(MultipartFile multipartFile, DocumentVersion documentVersion,
			StringBuffer fdestination, long documentRevisionNumber)
			throws IOException, SerialException, SQLException, NoSuchAlgorithmException {
		Date currentDate = new Date(System.currentTimeMillis());
		UserEntity currentUser = this.appService.getCurrentUser();
		MessageDigest md5Digest = MessageDigest.getInstance("MD5");
		DocumentContent content = new DocumentContent();
		Blob blob = new SerialBlob(multipartFile.getBytes());
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			//if (document.getIsOriginal() == IsOriginal.Yes && document.isFileToLocal()) {
				String ext = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf('.'), fileName.length())
						: "";
				String fName = fileName.contains(".") ? fileName.substring(0, fileName.lastIndexOf('.')) : fileName;
				String destination = fdestination.toString();// type.replace(" ", "");
				String fileName1 = fName + ext;
				fileName1 = fileName1.replace(":", "_");
				fileName1 = getFileName(destination,fileName1);;
//				String uploadPath = this.fileStorageLocation + "/" + destination;
//				File file1 = new File(uploadPath);
//				if (!file1.exists()) {
//					file1.mkdirs();
//					file1.setExecutable(true, false);
//					file1.setReadable(true, false);
//					file1.setWritable(true, false);
//				}
				Path targetLocation = this.fileStorageLocation.resolve(fileName1);
				Files.copy(multipartFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
				File files = new File(targetLocation.toString());
				if (files.exists()) {
					files.setExecutable(true, false);
					files.setWritable(true, false);
					files.setReadable(true, false);
				}
			//}
			content.setDocumentContent(blob);
			String fileHash = FileHashingMD.getFileChecksum(md5Digest, multipartFile.getBytes());
			documentVersion.setRevisionNumber(documentRevisionNumber);
			//document.setVersionUsedInBill(false);
			//document.setPreprocessedStatus(PreprocessStatus.No);
			documentVersion.setHashKey(fileHash);
			documentVersion.setDocumentContent(content);
			documentVersion.setCreatedBy(currentUser.getId());
			documentVersion.setModifiedBy(currentUser.getId());
			documentVersion.setCreatedDate(currentDate);
			documentVersion.setModifiedDate(currentDate);
			documentVersion.setStatus(2L);
			documentVersion.setFileName(fileName);
			documentVersion.setOriginalFileName(fileName);
		} catch (IOException ex) {
			documentVersion.setReasonForFailure("Could not store file " + fileName + " " + ex);
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
		return documentVersion;
	}

	public String storeFromDatabaseAsBlob(InputStream fin, String fileName, String destination)
			throws IOException, SerialException, SQLException, NoSuchAlgorithmException {
		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			String ext = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf('.'), fileName.length()) : "";
			String fName = fileName.contains(".") ? fileName.substring(0, fileName.lastIndexOf('.')) : fileName;
			String fileName1 = fName + ext;
			fileName1 = fileName1.replace(":", "_");
			fileName1 = getFileName(destination,fileName1);
//			String uploadPath = this.fileStorageLocation + "/" + destination;
//			File file1 = new File(uploadPath);
//			if (!file1.exists()) {
//				file1.mkdirs();
//				file1.setExecutable(true, false);
//				file1.setReadable(true, false);
//				file1.setWritable(true, false);
//			}
			Path targetLocation = this.fileStorageLocation.resolve(fileName1);
			System.out.println("Reupload file : " + targetLocation);
			Files.copy(fin, targetLocation, StandardCopyOption.REPLACE_EXISTING);
			File files = new File(targetLocation.toString());
			if (files.exists()) {
				files.setExecutable(true, false);
				files.setWritable(true, false);
				files.setReadable(true, false);
			}
		} catch (

		IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
		return null;
	}

	public File getFileStorageLocation(String string, String filename) {
		String uploadPath = this.fileStorageLocation+string;
		File file = new File(uploadPath);
		if (!file.exists()) {
			file.mkdirs();
			file.setExecutable(true, false);
			file.setReadable(true, false);
			file.setWritable(true, false);
		}
		file = new File(uploadPath+"/"+filename);
		// TODO Auto-generated method stub
		return file;
	}
	
	public String getFileName(String destination, String fileName) {
		String fileName1 = destination + "/" + fileName;
		this.getFileStorageLocation(destination, fileName);
		return fileName1;
	}
	
	public File getRealPath(String folderName, String fileName) {
		return new File(this.fileStorageLocation+folderName+"/"+fileName);
	}
}
