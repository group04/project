package com.groupproject.service;

import java.security.PublicKey;
import java.util.Arrays;

import javax.inject.Inject;

import com.groupproject.data.ClientDBRepository;
import com.groupproject.data.FileDBRepository;

public class VerifyEoR {
	@Inject
	ClientDBRepository clientdbrepository;
	@Inject
	FileDBRepository fileDBRepository;
	@Inject
	Communication communication;


	/**
	 * given the EOR file and verify the EOR return true or false
	 */
	public Boolean verify(byte[] eoR,String fileID,String clientID){
		byte[] pukey=CodeExchange.getbyte(clientdbrepository.getKey(clientID));
		PublicKey publicKey=Encrypty.getPublicKey(pukey);
		byte[] eoo=CodeExchange.getbyte(fileDBRepository.getfileEoo(fileID));
		byte[] eoo2=Encrypty.decrypt(publicKey, eoR);

		return Arrays.equals(eoo, eoo2);
		
	}
	
	/**
	 * give the EOR to the sender through the Email or SQS
	 * 
	 */
	public void sendEor(String fileID){
		communication.sendEor(fileID);

	}
}
