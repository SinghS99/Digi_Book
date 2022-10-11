package com.author.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordEncDec {

	public static String encryptingPassword(String password) {
		String originalPassword = password;
		String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
		// System.out.println(generatedSecuredPasswordHash);

		return generatedSecuredPasswordHash;

	}

	public static boolean bCrypter(String originalPassword, String generatedSecuredPasswordHash) {

//		boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
//		System.out.println(matched);

		if (BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash))
			return true;
		else
			return false;
	}
}
