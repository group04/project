package com.groupproject.service;

import org.apache.commons.codec.binary.Base64;




public class CodeExchange {
	/**
	 * static method change the String to byte[] using base64
	 */
	public static byte[] getbyte(String str){
		
		return Base64.decodeBase64(str);
		
	}
	/**
	 * static method change the byte[] to String using base64
	 * 
	 */
	public static String getString(byte[] by){
		return Base64.encodeBase64String(by);
		
	}

}
