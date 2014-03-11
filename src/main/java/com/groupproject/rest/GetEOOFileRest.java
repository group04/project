package com.groupproject.rest;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.groupproject.data.FileDBRepository;
import com.groupproject.data.FileStorage;
import com.groupproject.service.CodeExchange;

/**
 * this class is used to given a link to the client to download the EOO file
 * 
 * @author hp
 * 
 */
@Path("/get")
public class GetEOOFileRest {
	@Inject
	private FileDBRepository fileDBRespoitory;

	/**
	 * a request given a fileID and the clientID to get the EOO file
	 * 
	 */
	@POST
	@Path("/eoo")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadEoo(@FormParam("fileID") String fileID,
			@FormParam("clientID") String clientID) throws Exception {
		if (fileID != null) {
			if (clientID != null) {
				if (fileDBRespoitory.getReceiver(fileID).equals(clientID)) {
					String eooname = "eoo_for_"
							+ fileDBRespoitory.getName(fileID);
					byte[] docStream = CodeExchange.getbyte(fileDBRespoitory
							.getfileEoo(fileID));

					return Response
							.ok(docStream, MediaType.APPLICATION_OCTET_STREAM)
							.header("content-disposition",
									"attachment; filename = " + eooname)
							.build();
				}
			}
		}
		return Response.ok(200).entity("error").build();
	}
}
