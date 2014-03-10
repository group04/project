package com.groupproject.rest;

import javax.inject.Inject;
import javax.ws.rs.Path;

import com.groupproject.data.FileStorage;



/**
 * this class is used to given a link to the client to download the EOO file
 * @author hp
 *
 */
@Path("/get")
public class GetEOOFileRest {
	@Inject
	private FileStorage filestorage;
	/**
	 * a request given a fileID and the clientID to get the EOO file
	 * 
	 */
	@Path("/eoo")
	public void getEoo(String fileid){
		filestorage.getStream(fileid);
		
		
	}
	
	

}
