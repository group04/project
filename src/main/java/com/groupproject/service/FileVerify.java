package com.groupproject.service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.inject.Inject;

import com.groupproject.data.ClientDBRepository;
import com.groupproject.data.FileDBRepository;
import com.groupproject.data.FileStorage;
import com.groupproject.model.File;
import com.groupproject.rest.GenerateString;



public class FileVerify {
	@Inject
	private ClientDBRepository clientserver;
	@Inject
	private FileDBRepository fileexchangeserver;
	@Inject
	private Verify verifyfil;
	@Inject
	private FileStorage filestorage;
	@Inject
	private Communication communication;
	
	
	/**
	 *a method given a file and Sig(h(doc)) and clientsenderid 
	 *return true or false

	 */
	public Boolean verify(byte[] eoo,byte[] doc,String senderId){
		verifyfil.verify(eoo, doc, senderId);
		return null;
		
	}
	/**
	 * verify the sender and receiver is exist
	 * return fileID if both of them exist
	 */
	public Boolean check(String sender,String receiver){
		
		if(clientserver.SearchClient(sender)&&clientserver.SearchClient(receiver)){
		
			
			return true;
		
		}
		return false;
	}
	/**
	 * Randomly generate a fileID generate a 20 long Srting type(you can get the current time then hash the object)
	 * @param file
	 * @param senderID
	 * @return
	 */
	public String getFileID(){
		return GenerateString.generateString(20);
		
	}
	
	public String getFilekey(String fileid){
		String filekey=fileid+GenerateString.generateString(5);
		return filekey;
		
	}
	/**
	 * create an folder use the name of the fileID and Storage the doc in the catalog
	 * return the folder relative path
	 */
	public void storageDoc(String key,byte[] doc){
		filestorage.storage(key,doc); 

				
	}
	/**
	 * storage the FileExchange in the database
	 * 
	 */
	public void Stroage(File fileexchange){
		fileexchangeserver.save(fileexchange);
		
	}
	/**
	 * send URLto the communication class
	 * 
	 */
	public void SetEoo(String fileid){
		communication.sendEoo(fileid);
		
	}
	/**
	 * a method generate a directory and the name of the directory is fileID
	 * @return the URL address about the directory
	 */
	public String generateURL(String fileID){
		return null;
		
	}
	

}
