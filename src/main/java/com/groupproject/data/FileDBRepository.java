package com.groupproject.data;

import com.groupproject.model.File;


public class FileDBRepository {

	/**
	 * save the FileExchange class
	 */
	public void save(File fileexchange){
		
	}
	/**
	 * Update the Exchange state
	 * 
	 */
	public void updateState(String fileid,String state){
		
	}
	/**
	 * delete the exchange 
	 */
	public void deleteExchange(String fileid){
		
	}
	/**
	 * get the state of the exchange
	 */
	public String getState(String fileId){
		return null;
		
	}
	/**
	 * given a fileid get the Eoo
	 */
	
	public String getfileEoo(String fileid){
		return null;
		
	}
	/**
	 * given a fileid get the eor
	 */
	public String getfileeor(String fileid){
		return null;
	}
	/**
	 * get the name of the file
	 */
	public String getName(String fileid){
		return null;
				
	}
	/**
	 * get the sender
	 * @param fileid
	 * @return
	 */
	public String getSender(String fileid){
		return null;
	}
	/**
	 * get the receiver
	 */
	public String getReceiver(String fileid){
		return fileid;
		
	}
	/**
	 * update the eor
	 */
	public void updateEor(String eor){
		
	}
	/**
	 * get the key for the doc
	 */
	public String getdocKey(String fileid){
		return null;
		
	}
}
