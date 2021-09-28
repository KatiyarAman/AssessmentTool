package com.sterp.multitenant.utils;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class CommonUtils {
	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final String otp = "0123456789";
	
	public String generateRandomUserId(int length) {
		return generateRandomString(length);
	}

	public String generateRandomString(int length) {
		StringBuilder returnValue = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return new String(returnValue);
	}
	
	public String generateRandomOTPString(int length) {
		StringBuilder returnValue = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			returnValue.append(otp.charAt(RANDOM.nextInt(otp.length())));
		}
		return new String(returnValue);
	}

	static final private String PRODUCKKEY = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	final private Random rng = new SecureRandom();

	char randomChar() {
		return PRODUCKKEY.charAt(rng.nextInt(PRODUCKKEY.length()));
	}

	public String randomUUID(int length, int spacing, char spacerChar) {
		StringBuilder sb = new StringBuilder();
		int spacer = 0;
		while (length > 0) {
			if (spacer == spacing) {
				sb.append(spacerChar);
				spacer = 0;
			}
			length--;
			spacer++;
			sb.append(randomChar());
		}
		return sb.toString();
	}

//	public static void main(String[] args) {
//		System.out.println(new Utils().randomUUID(16, 4, '-'));
//	}
}
