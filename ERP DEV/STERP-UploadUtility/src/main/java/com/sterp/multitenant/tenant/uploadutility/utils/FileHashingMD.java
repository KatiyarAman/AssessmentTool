package com.sterp.multitenant.tenant.uploadutility.utils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileHashingMD {
	public static List<String> createFileHash(MultipartFile[] files) throws NoSuchAlgorithmException, IOException {
		MessageDigest md5Digest = MessageDigest.getInstance("MD5");
		List<String> hashValues = new ArrayList<String>();
		for (MultipartFile multipartFile : files) {
			String checksum = getFileChecksum(md5Digest, multipartFile.getBytes());
			System.out.println(checksum);
			hashValues.add(checksum);
		}
		return hashValues;
	}

	public static String getFileChecksum(MessageDigest digest, byte[] fileArray) throws IOException {
		InputStream targetStream = new ByteArrayInputStream(fileArray);
		byte[] byteArray = new byte[1024];
		int bytesCount = 0;
		while ((bytesCount = targetStream.read(byteArray)) != -1) {
			digest.update(byteArray, 0, bytesCount);
		}
		byte[] bytes = digest.digest();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
}
