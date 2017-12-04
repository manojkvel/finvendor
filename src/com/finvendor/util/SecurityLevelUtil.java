/**
 * 
 */
package com.finvendor.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;


/**
 * @author rayulu vemula
 *
 */
public class SecurityLevelUtil {

	public static Cipher dcipher, ecipher;

	public SecurityLevelUtil(String passPhrase) {
		byte[] salt = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
				(byte) 0x56, (byte) 0x34, (byte) 0xE3, (byte) 0x03 };
		int iterationCount = 19;
		try {
			KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt,
					iterationCount);
			SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
					.generateSecret(keySpec);
			ecipher = Cipher.getInstance(key.getAlgorithm());
			dcipher = Cipher.getInstance(key.getAlgorithm());
			AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt,
					iterationCount);
			ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
	}

	public String encrypt(String str) {
		try {
			byte[] utf8 = str.getBytes("UTF8");
			byte[] enc = ecipher.doFinal(utf8);
			//return new sun.misc.BASE64Encoder().encode(enc);
		} catch (BadPaddingException e) {e.printStackTrace();
		} catch (IllegalBlockSizeException e) {e.printStackTrace();
		} catch (UnsupportedEncodingException e) {e.printStackTrace();
		}
		return null;
	}
	public String decrypt(String str) {
		Cipher dcipher = null;
		try {
			byte[] salt = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
					(byte) 0x56, (byte) 0x34, (byte) 0xE3, (byte) 0x03 };
			int iterationCount = 19;
			try {
				String passPhrase = "";
				KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(),
						salt, iterationCount);
				SecretKey key = SecretKeyFactory
						.getInstance("PBEWithMD5AndDES")
						.generateSecret(keySpec);
				dcipher = Cipher.getInstance(key.getAlgorithm());
				AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt,
						iterationCount);
				dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
			} catch (InvalidAlgorithmParameterException e) {
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
			//byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
			byte[] utf8 = dcipher.doFinal(null);
			return new String(utf8, "UTF8");
		} catch (BadPaddingException e) {
		} catch (IllegalBlockSizeException e) {
		} catch (UnsupportedEncodingException e) {
		} catch (IOException e) {
		}
		return null;
	}

	 

}
