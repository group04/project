package com.groupproject.rest;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


public class GetEORFileRest {
	@Inject

	/**
	 * POST The fileId , clientidsender .
	 * @return URL for the EOR
	 */

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getEOR(String id,String clientsender) {

		

		return null;
	}
}
