package com.bankcomm.framework.encrypt;

import android.util.Base64;

import com.bankcomm.framework.log.AriesLog;
import com.bankcomm.framework.utils.ArrayUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

/**
 * 用户客户端对数据使用证书公钥加密
 * 
 * @author iven
 * 
 */
public class RSACrypt {
	/**
	 * 密钥算法
	 */
	private static final String KEY_ALGORITHM = "RSA";
	private static final String DEFAULT_CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
	private Cipher cipher;
	private static RSACrypt rsaPlus = null;

	private RSACrypt() {

	}

	public static RSACrypt getInstance() {
		if (null == rsaPlus) {
			rsaPlus = new RSACrypt();
			try {
				rsaPlus.initPemKey(null);
			} catch (Exception e) {
				AriesLog.e("TAG", "init the cer ERROR!", e);
			}
		}
		return rsaPlus;
	}
	
	public static RSACrypt getInstance(InputStream pem) {
		if (null == rsaPlus) {
			rsaPlus = new RSACrypt();
			try {
				rsaPlus.initPemKey(pem);
			} catch (Exception e) {
				AriesLog.e("TAG", "init the cer ERROR!", e);
			}
		}
		return rsaPlus;
	}

	/**
	 * 初始化加载pem证书
	 * 
	 * @Description
	 *  app
	 * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-12-11
	 */
	private void initPemKey(InputStream pem) throws Exception {
		InputStream is = null;
		if(null == pem) {
			//change by liuhailin for public key start
			//is = getClass().getResourceAsStream("public_key.pem");
			is = new ByteArrayInputStream(com.bankcomm.framework.encrypt.PublicKey.PUBLIC_KEY.getBytes());
			//change by liuhailin for public key end
		}else {
			is = pem;
		}
//		InputStream is = resources.openRawResource(R.raw.public_key); // 证书文件
		byte buffer[] = new byte[is.available()];
		is.read(buffer);
		byte[] newByte = Base64.decode(new String(buffer, "utf-8"), Base64.NO_WRAP);// 没有换行
		KeyFactory pubKeyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(newByte);
		RSAPublicKey publicKey = (RSAPublicKey) pubKeyFactory.generatePublic(pubSpec);
		cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM); // 定义算法：RSA
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	}

	/**
	 * 初始化加载cer证书
	 * 
	 * @Description
	 *  app
	 * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-12-11
	 */
	private void initCerKey() throws Exception {

		CertificateFactory cff = CertificateFactory.getInstance("X.509");
		//change by liuhailin for public key start
		//InputStream in = getClass().getResourceAsStream("public_key.pem");
		InputStream in = new ByteArrayInputStream(com.bankcomm.framework.encrypt.PublicKey.PUBLIC_KEY.getBytes());
		//change by liuhailin for public key end
//		InputStream in = app.getBaseContext().getResources().openRawResource(R.raw.public_key);
		// 证书文件
		Certificate cf = cff.generateCertificate(in);
		PublicKey pk1 = cf.getPublicKey(); // 得到证书文件携带的公钥
		cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM); // 定义算法：RSA
		cipher.init(Cipher.ENCRYPT_MODE, pk1);

	}

	/**
	 * 使用初始化的公钥对数据加密
	 * 
	 *  str
	 * @return
	 * @throws Exception
	 *             : IllegalBlockSizeException, BadPaddingException,
	 *             UnsupportedEncodingException
	 */
	public String doEncrypt(String encStr) {
		// 分段加密
		byte[] data;
		byte[] encryptdata = null;
		try {
			data = encStr.getBytes("UTF-8");

			for (int i = 0; i < data.length; i += 100) {
				byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + 100));
				encryptdata = ArrayUtils.addAll(encryptdata, doFinal);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Base64.encodeToString(encryptdata, Base64.NO_WRAP);
	}

	/**
	 * 使用初始化的公钥对数据解密
	 * 
	 * @Description
	 * @param decStr
	 * @return
	 * @throws Exception
	 * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-12-10
	 */
	public String doDecrypt(String decStr) {
		byte[] decryptData = null;
		byte[] data;
		try {
			data = decStr.getBytes("UTF-8");
			decryptData = cipher.doFinal(data);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Base64.encodeToString(decryptData, Base64.NO_WRAP);
	}
}
