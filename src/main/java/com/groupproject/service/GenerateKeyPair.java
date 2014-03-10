package com.groupproject.service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;

public class GenerateKeyPair {
	private byte[] privatekey;
	private byte[] publickey;
	public PrivateKey privatek;
	public PublicKey publick;

	public byte[] getPrivatekey() {
		return privatekey;
	}

	public byte[] getPublickey() {
		return publickey;
	}

	private String clientID;

	/**
	 * construction give a client id generate a pair Key
	 * 
	 */
	public GenerateKeyPair(String clientID) throws NoSuchAlgorithmException {
		this.clientID = clientID;
		generate(clientID);
	}

	/**
	 * based on the clientID generate two key and storage in in the privatekey
	 * and publickey.
	 * 
	 * @param clientID
	 */
	public void generate(String clientID) throws NoSuchAlgorithmException {

		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		SecureRandom securerandom = new SecureRandom(clientID.getBytes());
		keyGen.initialize(1024,securerandom);
		KeyPair keypair=keyGen.generateKeyPair();
		privatek=keypair.getPrivate();
		publick=keypair.getPublic();
		publickey = keypair.getPublic().getEncoded();
		privatekey =keypair.getPrivate().getEncoded();

	}

	public static void main(String[] arg) throws NoSuchAlgorithmException,
			InvalidKeySpecException, InvalidKeyException,
			NoSuchPaddingException, ShortBufferException,
			IllegalBlockSizeException, BadPaddingException, IOException, NoSuchProviderException {
		String num = "2323";
		GenerateKeyPair pair = new GenerateKeyPair(num);
		//System.out.println("public key "
				//+ CodeExchange.getString(pair.getPublickey()));
		//System.out.println("private key "
				//+ CodeExchange.getString(pair.getPrivatekey()));
		String content = "hello world";
		byte[] con = CodeExchange.getbyte(content);
		//byte[] con = content.getBytes("UTF-8");
		//System.out.println(new String(con));
		PrivateKey privatekey=Encrypty.getPrivateKey(pair.getPrivatekey());
		byte[] enciptext = Encrypty.encrypt(privatekey, con);
		System.out.println(new String(enciptext));
		PublicKey publicKey = Encrypty.getPublicKey(pair.getPublickey());
		byte[] plain=Encrypty.decrypt(publicKey, enciptext);
		//System.out.println(new String(plain,"UTF-8"));
		System.out.println(CodeExchange.getString(plain));

	}

} 
