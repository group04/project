package com.groupproject.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.groupproject.service.VerifyEoR;


public class PostEoRFileRest {
	@Inject
	private VerifyEoR verifyeor;
	
	/**
	 * a method post the EoR file and the fileId.
	 *  check whether the exchange is abort
	 * @param 
	 * @return return the URI for the Doc file
	 */
	@POST

	public String GetEorFile( ) {
		byte[] EoR = null;
		String fileid = null;
		verifyeor.verify(EoR, fileid);
		
		return null;
	}
}
