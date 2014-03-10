package com.groupproject.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.groupproject.data.ClientDBRepository;
import com.groupproject.data.FileDBRepository;
import com.groupproject.data.FileStorage;
import com.groupproject.email.SimpleMailSender;
import com.groupproject.service.VerifyEoR;


public class PostEoRFileRest {
	@Inject
	private VerifyEoR verifyeor;
	@Inject
	private FileDBRepository fileDBRepository;
	@Inject
	private FileStorage fileStorage;
	
	/**
	 * a method post the EoR file and the fileId clientId
	 *  check whether the exchange is abort
	 * @param 
	 * @return return the URI for the Doc file
	 */
	@POST

	public byte[] GetEorFile( ) {
		byte[] EoR = null;
		String fileid = null;
		String clientID=null;
		verifyeor.verify(EoR, fileid,clientID);
		String senderemail=fileDBRepository.getSender(fileid);
		verifyeor.sendEor(senderemail);
		return fileStorage.getStream(fileid);
	}
}
