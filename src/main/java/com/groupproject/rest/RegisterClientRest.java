package com.groupproject.rest;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.groupproject.model.Client;
import com.groupproject.service.ProcessClient;


@Path("/client")
public class RegisterClientRest {

	@Inject
	private ProcessClient processClient;

	/**
	 * get the request Client
	 * @param id
	 * @return the all the privateKey of the client.
	 */
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response storePKey(@FormParam("id") String id,
		@FormParam("pKey") String pKey) throws Exception {
		if(!processClient.verify(id)) //verify the id  whether the id has already in used.
		{
			Client client = new Client();
			client.setClientID(id);
			client.setPublicKey(pKey);
			processClient.saveClientToDB(client);
			return Response.ok(200).entity(id + "registered").build();
		}			
		return Response.ok(999).entity("Error: Client already exists").build();
	}	
}
