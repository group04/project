package com.groupproject.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.groupproject.service.ProcessClient;


@Path("/register")
public class RegisterClientRest {
		@Inject
		private ProcessClient processClient;

		/**
		 * Receive client registration request. The method verifies whether the client is already 
		 * registered. 
		 * @param id
		 * @return response.
		 */
		@GET
		@Path("/{id}")
		@Produces(MediaType.TEXT_HTML)
		public Response creatClient(@PathParam("id") String id) {			
			if(!processClient.verify(id))    
			{
				//register client;
			return Response.ok("success").build();
			}
			
			return Response.ok("success").build();
		}
}
