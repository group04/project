package com.groupproject.service;

public class Communication {
	
	private final String urlEoo="";
	private final String urlEor="";
	private final String urlDoc="";
/**
 * given a relation path of a file generate a new different(Important!!) absolute path
 *  return absolute path
 */
	public String getAbsoultionpath(String path){
		return path;
		
	}
	/**
	 * send the URL and the fileID to the client through the SQS or Email
	 * 
	 */
	public void Send(String email,String content){
		
	}
	/**
	 * sent the eoo
	 * @param fileid
	 */
	public void sendEoo(String fileid){
		
	}
	/**
	 * sent the eor
	 */
	public void sendEor(String fileid){
		
		
	}
}
