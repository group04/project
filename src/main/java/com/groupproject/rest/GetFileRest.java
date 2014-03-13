package com.groupproject.rest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.io.IOUtils;

import com.groupproject.data.FileDBRepository;
import com.groupproject.data.FileManager;
import com.groupproject.service.CodeExchange;
import com.groupproject.service.VerifyEoR;
import com.sun.jersey.multipart.FormDataParam;

@Path("/post")
public class GetFileRest {
	@Inject
	private VerifyEoR verifyeor;
	@Inject
	private FileDBRepository fileDBRepository;
	@Inject
	private FileManager fileManager;

	/**
	 * a method post the EoR file and the fileId clientId check whether the
	 * exchange is abort
	 * 
	 * @param
	 * @return return the URI for the Doc file
	 * @throws IOException 
	 */
	@POST
	@Path("/file")
	@Produces(MediaType.MULTIPART_FORM_DATA)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response GetEorFile(@FormDataParam("eor") InputStream eorStream,
			@FormDataParam("fileid") InputStream fileidStream,
			@FormDataParam("clientId") InputStream clientStream)
			throws IOException {

		byte[] EoR = IOUtils.toByteArray(eorStream);
		String fileid = inputStream2String(fileidStream);
		String clientID = inputStream2String(clientStream);
		if (verifyeor.verify(EoR, fileid, clientID)) {
			verifyeor.updateeor(fileid,CodeExchange.getString(EoR));
			String senderemail = fileDBRepository.getSender(fileid);
			verifyeor.sendEor(senderemail, fileid);
			String dockey = fileDBRepository.getdocKey(fileid);
			String namedoc=fileDBRepository.getName(fileid);
		    File file = fileManager.downloadFile(dockey);
		    ResponseBuilder response = Response.ok((Object) file);
		    response.header("Content-Disposition", "attachment; filename="+namedoc);
			return response.build();
		}
		return Response.ok(200).entity("error: " + fileDBRepository.getName(fileid) + " not found").build();
	}
	
	public static String inputStream2String(InputStream is) throws IOException{
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 int i=-1;
		 while((i=is.read())!=-1){
		 baos.write(i);
		 }
		 return baos.toString();
		 } 
}
