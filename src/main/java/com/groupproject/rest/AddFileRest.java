package com.groupproject.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;



//import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;

import com.groupproject.model.File;
import com.groupproject.service.CodeExchange;
import com.groupproject.service.FileVerify;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;



@Path("/upload")
public class AddFileRest {
	@Inject
	private FileVerify verifyFile;
	/**
	 * File rest service for storing the file into the DB
	 * @param fileInputStream
	 * @param fileDetail
	 * @param eooStream
	 * @param senderStream
	 * @param receiverStream
	 * @return
	 * @throws IOException
	 */
	@POST
	@Path("/file")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response upLoadclientID(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,
			@FormDataParam("eoo") InputStream eooStream,
			@FormDataParam("sender") InputStream senderStream,
			@FormDataParam("receiver") InputStream receiverStream
			) throws IOException{
		String receiver = inputstreamToString(receiverStream);
		String sender = inputstreamToString(senderStream);
		byte[] eoo = IOUtils.toByteArray(eooStream);;
		byte[] doc = IOUtils.toByteArray(fileInputStream);
		String filename = fileDetail.getFileName();

		if (verifyFile.verifySender(sender, receiver)) {
			if (verifyFile.verify(eoo, doc, sender)) {
				String fileid = verifyFile.getFileID();
				String fileKey=verifyFile.getFilekey(fileid);
				//String Url=verifyfile.generateURL(fileid);
				File fileexchange = new File();
				fileexchange.setClientRecever(receiver);
				fileexchange.setClientSender(sender);
				fileexchange.setFileID(fileid);
				fileexchange.setFilename(filename);
				fileexchange.setEoo(CodeExchange.getString(eoo));
				fileexchange.setKeyfordoc(fileKey);
				verifyFile.store(fileexchange);
				verifyFile.storeFile(fileKey,doc);
				verifyFile.SetEoo(sender,fileid);
				return Response.status(200).entity(filename + "was successfully stored").build();
			}
		}
		return null;
	}
	
	/**
	 * Convert inputstream into string
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static String inputstreamToString(InputStream is) throws IOException{
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 int i=-1;
		 while((i=is.read())!=-1){
		 baos.write(i);
		 }
		 return baos.toString();
		 } 


}
