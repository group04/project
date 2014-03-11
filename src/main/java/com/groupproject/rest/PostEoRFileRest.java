package com.groupproject.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;

import com.groupproject.data.ClientDBRepository;
import com.groupproject.data.FileDBRepository;
import com.groupproject.data.FileStorage;
import com.groupproject.email.SimpleMailSender;
import com.groupproject.service.CodeExchange;
import com.groupproject.service.VerifyEoR;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/post")
public class PostEoRFileRest {
	@Inject
	private VerifyEoR verifyeor;
	@Inject
	private FileDBRepository fileDBRepository;
	@Inject
	private FileStorage fileStorage;

	/**
	 * a method post the EoR file and the fileId clientId check whether the
	 * exchange is abort
	 * 
	 * @param
	 * @return return the URI for the Doc file
	 * @throws IOException 
	 */
	@POST
	@Path("/eor")
	@Produces(MediaType.MULTIPART_FORM_DATA)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response GetEorFile(@FormDataParam("eor") InputStream eorStream,
			@FormDataParam("fileid") InputStream fileidStream,
			@FormDataParam("clientId") InputStream clientStream) throws IOException {
		
		byte[] EoR = IOUtils.toByteArray(eorStream);
		String fileid = inputStream2String(fileidStream);
		String clientID = inputStream2String(clientStream);
		if(verifyeor.verify(EoR, fileid, clientID)){
			verifyeor.updateeor(CodeExchange.getString(EoR));
		String senderemail = fileDBRepository.getSender(fileid);
		verifyeor.sendEor(senderemail,fileid);	
		String dockey=fileDBRepository.getdocKey(fileid);
		String docname=fileDBRepository.getName(fileid);
		byte[] docStream = fileStorage.getStream(dockey);
		

		return Response
				.ok(docStream, MediaType.APPLICATION_OCTET_STREAM)
				.header("content-disposition",
						"attachment; filename = " + docname)
				.build();
		
		
		}
		return Response
				.ok(200).entity("error")
				.build();
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
