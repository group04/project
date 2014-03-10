package com.groupproject.service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.inject.Inject;

import com.groupproject.data.ClientDBRepository;


public class Verify {
	@Inject
	private ClientDBRepository clientserver;

	
/**
 * give the clientID file and the encryption file
 * return true  if the encryption file is come from the client  or false
 */
	public Boolean verify(byte[] eoo,byte[] doc,String ClientID){
		String publickey=clientserver.getKey(ClientID);
		byte[] pubkey=CodeExchange.getbyte(publickey);
		//verify the eoo
		byte[] hash=Encrypty.getbyte(doc);
		PublicKey publickk=Encrypty.getPublicKey(pubkey);
		byte[] eoo2=Encrypty.decrypt(publickk, hash);
		
		return Arrays.equals(eoo, eoo2);
		
	}
}
