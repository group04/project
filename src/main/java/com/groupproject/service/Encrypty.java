package com.groupproject.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.DigestException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;

public class Encrypty {
	public static byte[] encrypt(PrivateKey pk, byte[] data) {
		byte[] re = null;

		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, pk);
			re = cipher.doFinal(data);
		} catch (Exception e) {

		}
		return re;

	}

	public static byte[] decrypt(PublicKey pk, byte[] raw){

		byte[] re=null;
		try{
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(cipher.DECRYPT_MODE, pk);
			re=cipher.doFinal(raw);

		}catch(Exception e){
			
		}
			return re;
		
	}

	public static PublicKey getPublicKey(byte[] by)
			{
		X509EncodedKeySpec publickeyspec = new X509EncodedKeySpec(by);
		KeyFactory keyFactory = null;
		try {
			keyFactory = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PublicKey publickk = null;
		try {
			publickk = keyFactory.generatePublic(publickeyspec);
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return publickk;

	}

	public static PrivateKey getPrivateKey(byte[] by)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		PKCS8EncodedKeySpec privatekeyspec = new PKCS8EncodedKeySpec(by);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privatekk = keyFactory.generatePrivate(privatekeyspec);
		return privatekk;

	}

	public static byte[] getbyte(byte[] file) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		md.update(file);
		byte[] Digest = md.digest();
		return Digest;

	}
}
