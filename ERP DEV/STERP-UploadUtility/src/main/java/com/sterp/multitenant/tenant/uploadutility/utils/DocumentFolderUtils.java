package com.sterp.multitenant.tenant.uploadutility.utils;

public class DocumentFolderUtils {
	private String[] specialCharacters;

	public DocumentFolderUtils(String[] specialCharacters) {
		this.specialCharacters = new String[] { "\\", "/", ":", "*", "?", "\"", "<", ">", "|" };
	}

	public String[] getSpecialCharacters() {
		return specialCharacters;
	}

	public void setSpecialCharacters(String[] specialCharacters) {
		this.specialCharacters = specialCharacters;
	}

}
