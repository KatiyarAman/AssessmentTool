package com.sterp.multitenant.tenant.core.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sterp.multitenant.tenant.config.FileStorageProperties;
import com.sterp.multitenant.tenant.exceptionhandler.CustomException;
import com.sterp.multitenant.tenant.exceptionhandler.FileStorageException;

@Service
public class FileStorageServiceImpl {

	private final Path fileStorageLocation;

	@Autowired
	ServletContext ctx;

	@Autowired
	public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	public List<Object> storeFile(MultipartFile[] file, String username, String destinationfolder, String companyName)
			throws NoSuchAlgorithmException {
		if (file == null) {
			throw new CustomException("Files Not found");
		}
		// int x = 0;

		List<Object> fileHash = new ArrayList<Object>();
		MessageDigest md5Digest = MessageDigest.getInstance("MD5");
		for (MultipartFile multipartFile : file) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			try {
				// Date current = new Date(System.currentTimeMillis());
				if (fileName.contains("..")) {
					throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
				}
				String ext = fileName.substring(fileName.indexOf('.'), fileName.length());
				String fName = fileName.substring(0, fileName.lastIndexOf('.'));
				String destination = companyName + "/" + username + "/" + destinationfolder;// type.replace(" ", "");

				String fileName1 = fName + ext;
				fileName1 = fileName1.replace(":", "_");
				fileName1 = destination + "/" + fileName1;
				String uploadPath = this.fileStorageLocation + "/" + destination;
				File file1 = new File(uploadPath);
				if (!file1.exists()) {
					file1.mkdirs();
					file1.setExecutable(true, false);
					file1.setReadable(true, false);
					file1.setWritable(true, false);
				}
				Path targetLocation = this.fileStorageLocation.resolve(fileName1);
				Files.copy(multipartFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
				File files = new File(targetLocation.toString());
				if (files.exists()) {
					files.setExecutable(true, false);
					files.setWritable(true, false);
					files.setReadable(true, false);
				}

//				fileHash.add(FileHashingMD.getFileChecksum(md5Digest, multipartFile.getBytes()));

			} catch (IOException ex) {
				throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
			}
			// x++;
		}
		return fileHash;
	}
	
	
	

//	public Attachment storeSingleFile(MultipartFile file, List<Integer> documentType, String type, String username) {
//		Attachment attachment1 = new Attachment();
//		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//		try {
//			Date current = new Date(System.currentTimeMillis());
//			if (fileName.contains("..")) {
//				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//			}
//			String ext = fileName.substring(fileName.indexOf('.'), fileName.length());
//			String fName = fileName.substring(0, fileName.lastIndexOf('.'));
//			String moduleFolder = type.replace(" ", "");
//
//			String Docpath = "bankdetail";
//			String fileName1 = fName + current + "_" + ext;
//			fileName1 = fileName1.replace(":", "_");
//			fileName1 = "uploads/" + moduleFolder + "/" + Docpath + "/" + username + "/" + fileName1.replace(" ", "_");
//			String uploadPath = this.fileStorageLocation + "/uploads/" + moduleFolder + "/" + Docpath + "/" + username;
//			File file1 = new File(uploadPath);
//			if (!file1.exists()) {
//				file1.mkdirs();
//				file1.setExecutable(true, false);
//				file1.setReadable(true, false);
//				file1.setWritable(true, false);
//			}
//			Path targetLocation = this.fileStorageLocation.resolve(fileName1);
//			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//			File files = new File(targetLocation.toString());
//			if (files.exists()) {
//				files.setExecutable(true, false);
//				files.setWritable(true, false);
//				files.setReadable(true, false);
//			}
//			attachment1.setFileName(fileName);
//			attachment1.setFileUrl(fileName1.toString());
//			attachment1.setStatus(1);
//			attachment1.setDocTypeId(0);
//		} catch (IOException ex) {
//			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
//		}
//		return attachment1;
//	}
}
