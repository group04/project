package com.groupproject.rest;

import javax.inject.Inject;

import com.groupproject.data.FileStorage;



/**
 * this class is used to given a link to the client to download the EOO file
 * @author hp
 *
 */
public class GetEOOFileRest {
	@Inject
	private FileStorage filestorage;
	/**
	 * a request given a fileID and the clientID to get the EOO file
	 * 
	 */
	public void getEoo(String fileid){
		filestorage.getEOOStream(fileid);
		
		
	}

}
