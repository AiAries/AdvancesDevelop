package com.bankcomm.framework.encrypt;

import android.util.Base64;

import com.bankcomm.framework.log.AriesLog;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加解密,此加密类，是针对网络请求，报文加密使用
 * 
 * @Description
 * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-12-10
 * @Class AESCrypt Copyright (c) 2014 Shanghai P&C Information Technology
 *        Co.,Ltd. All rights reserved.
 */
public final class AESCryptUtil {
	private static final String TAG = "AESCryptUtil";
	private static final String AES_MODE = "AES/ECB/PKCS7Padding";// 加密算法/工作模式/填充方式
	private static final String CHARSET = "UTF-8";

	// AESCrypt-ObjC uses SHA-256 (and so a 256-bit key)
	private static final String HASH_ALGORITHM = "SHA-256";
	public static boolean DEBUG_LOG_ENABLED = false;

	/**
	 * 生成256位的密钥
	 * 
	 * @Description
	 * @param password
	 *            密钥
	 * @return password 的SHA256 值
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-12-10
	 */
	private static SecretKeySpec generateKey(final String password) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		SecretKeySpec secretKeySpec = null;
		try {
			final MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			byte[] bytes = password.getBytes("UTF-8");
//			digest.update(bytes, 0, bytes.length);
//			byte[] key = digest.digest();
//			log("SHA-256 key ", key);
			secretKeySpec = new SecretKeySpec(password.getBytes("UTF-8"), "AES");
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return secretKeySpec;
	}

	/**
	 * 加密算法
	 * 
	 * @Description
	 * @param password
	 *            密钥
	 * @param message
	 *            需要加密的内容
	 * @return
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-12-10
	 */
	public static String encrypt(final String password, String message) {
		String encryData = encrypt(password, message.getBytes());
		return encryData;
	}

	/**
	 * 加密算法
	 * 
	 * @Description
	 * @param password
	 *            密钥
	 * @param message
	 *            需要加密的内容
	 * @return
	 * @throws GeneralSecurityException
	 * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-12-10
	 */
	public static String encrypt(final String password, byte[] message) {
		String encoded = null;
		try {
			// 生成密钥 key
			final SecretKeySpec key = generateKey(password);
			log("message", message);
			// 加密
			byte[] cipherText = encrypt(key, message);
			// 将加密bytes转为字符串
			encoded = Base64.encodeToString(cipherText, Base64.NO_WRAP);
			log("Base64.NO_WRAP", encoded);

		} catch (UnsupportedEncodingException e) {
			if (DEBUG_LOG_ENABLED)
				AriesLog.e(TAG, "UnsupportedEncodingException ", e);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encoded;
	}

	/**
	 * 加密
	 * 
	 * @Description
	 * @param key
	 *            AES key typically 128, 192 or 256 bit
	 *  初始化向量时用
	 * @param message
	 *            待加密数据
	 * @return 返回加密后的 bytes数组
	 * @throws GeneralSecurityException
	 * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-12-10
	 */
	public static byte[] encrypt(final SecretKeySpec key, final byte[] message) throws GeneralSecurityException {
		final Cipher cipher = Cipher.getInstance(AES_MODE);
		// 使用密钥初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, key);
		// 执行操作
		byte[] cipherText = cipher.doFinal(message);
		return cipherText;
	}

	/**
	 * AES解密
	 * 
	 * @Description
	 * @param password
	 *            解密密钥
	 * @param base64EncodedCipherText
	 *            待解密数据
	 * @return
	 * @throws GeneralSecurityException
	 * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-12-10
	 */
	public static String decrypt(final String password, String base64EncodedCipherText) {
		try {
			// 还原密钥
			final SecretKeySpec key = generateKey(password);
			// 转换为byte数组
			byte[] decodedCipherText = Base64.decode(base64EncodedCipherText, Base64.NO_WRAP);
			byte[] decryptedBytes = decrypt(key, decodedCipherText);
			// 将解密后的字节数组转换为字符串
			String message = Base64.encodeToString(decryptedBytes, Base64.NO_WRAP);
			byte[] tmp = Base64.decode(message, Base64.DEFAULT);
			return new String(tmp);
		} catch (UnsupportedEncodingException e) {
			if (DEBUG_LOG_ENABLED)
				AriesLog.e(TAG, "UnsupportedEncodingException ", e);
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
			AriesLog.e(TAG, "Exception", e);
		}
		return "";
	}

	public static String decrypt(final String password, byte[] base64EncodedCipherText) {
		try {
			// 还原密钥
			final SecretKeySpec key = generateKey(password);
			// 转换为byte数组
			byte[] decryptedBytes = decrypt(key, base64EncodedCipherText);
			// 将解密后的字节数组转换为字符串
			String message = Base64.encodeToString(decryptedBytes, Base64.NO_WRAP);
			// String message = new String(decryptedBytes, CHARSET);
			return message;
		} catch (UnsupportedEncodingException e) {
			if (DEBUG_LOG_ENABLED)
				AriesLog.e(TAG, "UnsupportedEncodingException ", e);
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 解密
	 * 
	 * @Description
	 * @param key
	 *            AES key typically 128, 192 or 256 bit
	 *
	 *            生成向量时使用
	 * @param decodedCipherText
	 *            待解密数据
	 * @return
	 * @throws GeneralSecurityException
	 * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-12-10
	 */
	public static byte[] decrypt(final SecretKeySpec key, final byte[] decodedCipherText)
			throws GeneralSecurityException {
		final Cipher cipher = Cipher.getInstance(AES_MODE);
		cipher.init(Cipher.DECRYPT_MODE, key);
		// 开始执行
		byte[] decryptedBytes = cipher.doFinal(decodedCipherText);
		return decryptedBytes;
	}

	/**
	 * byte 数组转换为十六进制
	 * 
	 * @Description
	 * @param bytes
	 * @return
	 * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-12-10
	 */
	private static String bytesToHex(byte[] bytes) {
		final char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] hexChars = new char[bytes.length * 2];
		int v;
		for (int j = 0; j < bytes.length; j++) {
			v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	private static void log(String what, byte[] bytes) {
		if (DEBUG_LOG_ENABLED)
			AriesLog.d(TAG, what + "[" + bytes.length + "] [" + bytesToHex(bytes) + "]");
	}

	private static void log(String what, String value) {
		if (DEBUG_LOG_ENABLED)
			AriesLog.d(TAG, what + "[" + value.length() + "] [" + value + "]");
	}

	private AESCryptUtil() {
	}
}