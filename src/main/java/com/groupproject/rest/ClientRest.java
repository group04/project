package com.groupproject.rest;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.groupproject.service.ProcessClient;


@Path("/client")
public class ClientRest {

	@Inject
	private ProcessClient processClient;

	/**
	 * get the request Client
	 * @param id
	 * @return the all the privateKey of the client.
	 */
	@POST
	@Path("POST")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response storePKey(@FormParam("id") String id,
			@FormParam("pKey") String pKey) throws Exception {
		
		if(!processClient.verify(id)) //verify the id  whether the id has already in used.
		{
			return Response.ok(200).entity("Client not registered").build();
		}			
		//strore the public key into the DB
		return Response.ok(200).entity("error").build();
	}	
}
