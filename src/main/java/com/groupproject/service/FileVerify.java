package com.groupproject.service;

import java.io.IOException;

import javax.inject.Inject;

import com.groupproject.data.ClientDBRepository;
import com.groupproject.data.FileDBRepository;
import com.groupproject.data.FileManager;
import com.groupproject.model.File;



public class FileVerify {
	@Inject
	private ClientDBRepository clientDBRepository;
	@Inject
	private FileDBRepository fileDBRepository;
	@Inject
	private Verify verifyFile;
	@Inject
	private FileManager fileManager;
	@Inject
	private Communication communication;
	
	
	/**
	 * Calls a method that authenticate the origin of the method
	 * @param eoo
	 * @param doc
	 * @param senderId
	 * @return boolean
	 */
	public Boolean verify(byte[] eoo,byte[] doc,String senderId){
		verifyFile.verify(eoo, doc, senderId);
		return null;		
	}
	
	/**
	 * Checks if both sender and receiver exist
	 * @param sender
	 * @param receiver
	 * @return boolean
	 */
	public Boolean verifySender(String sender,String receiver){
		
		if(clientDBRepository.SearchClient(sender)&&clientDBRepository.SearchClient(receiver)){			
			return true;	
		}
		return false;
	}
	
	/**
	 * Randomly generate a fileID generate a 20-character long string.
	 * @param file
	 * @param senderID
	 * @return string
	 */
	public String getFileID(){
		return GenerateString.generateString(20);
		
	}
	
	/**
	 * Get a file key that is used to store the file in S3
	 * @param fileid
	 * @return string
	 */
	public String getFilekey(String fileid){
		String filekey=fileid+GenerateString.generateString(5);
		return filekey;
		
	}
	
	/**
	 * create an folder use the name of the fileID and Storage the doc in the catalog
	 * return the folder relative path
	 * @throws IOException 
	 */
	public void storeFile(String key,byte[] doc){
		try {
			fileManager.uploadFile(doc,key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 				
	}
	/**
	 * storage the FileExchange in the database
	 * 
	 */
	public void store(File fileexchange){
		fileDBRepository.save(fileexchange);		
	}
	/**
	 * send URLto the communication class
	 * 
	 */
	public void SetEoo(String clientid,String fileid){
		communication.sendEoo(clientid,fileid);
		
	}
	/**
	 * a method generate a directory and the name of the directory is fileID
	 * @return the URL address about the directory
	 */
	public String generateURL(String fileID){
		return null;
		
	}
	

}
