package com.groupproject.rest;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.groupproject.data.FileDBRepository;
import com.groupproject.data.FileManager;
import com.groupproject.service.CodeExchange;
import com.groupproject.service.VerifyEoR;

@Path("/geteor")
public class GetEorRest {
	@Inject
	private VerifyEoR verifyeor;
	@Inject
	private FileDBRepository fileDBRepository;


	/**
	 * POST The fileId , clientidsender .
	 * @return URL for the EOR
	 */

	@POST
	@Path("/eor")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadEoo(@FormParam("fileID") String fileID,
			@FormParam("clientID") String clientID) throws Exception {
		if (fileID != null) {
			if (clientID != null) {
				if (fileDBRepository.getSender(fileID).equals(clientID)) {
					String eooname = "eoo_for_"
							+ fileDBRepository.getName(fileID);
					byte[] docStream = CodeExchange.getbyte(fileDBRepository.getfileeor(fileID));

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
