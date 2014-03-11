package com.groupproject.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
//import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;

import com.groupproject.data.FileDBRepository;
import com.groupproject.model.File;
import com.groupproject.service.CodeExchange;
import com.groupproject.service.FileVerify;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;



@Path("/upload")
public class FileRest {
	@Inject
	private FileVerify verifyfile;
	@Inject
	private FileDBRepository fileexchangeserver;

	/**
	 * POST the clientidsender , clientidreveiver SigA(H(doc)) file,and the doc.
	 * (this area I suggest the resteasy to do it) this link is how to do it
	 * http://blog.csdn.net/jackyrongvip/article/details/6670500
	 * @throws IOException 
	 */
	@POST
	@Path("/file")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String upLoadclientID(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,
			@FormDataParam("eoo") InputStream eooStream,
			@FormDataParam("sender") InputStream senderStream,
			@FormDataParam("receiver") InputStream receiverStream
			) throws IOException{
		String receiver = inputStream2String(receiverStream);
		String sender = inputStream2String(senderStream);
		byte[] Eoo = IOUtils.toByteArray(eooStream);;
		byte[] doc = IOUtils.toByteArray(uploadedInputStream);
		String filename = fileDetail.getFileName();

		if (verifyfile.check(sender, receiver)) {
			if (verifyfile.verify(Eoo, doc, sender)) {
				String fileid = verifyfile.getFileID();
				String fileKey=verifyfile.getFilekey(fileid);
				//String Url=verifyfile.generateURL(fileid);
				File fileexchange = new File();
				fileexchange.setClientRecever(receiver);
				fileexchange.setClientSender(sender);
				fileexchange.setFileID(fileid);
				fileexchange.setFilename(filename);
				fileexchange.setEoo(CodeExchange.getString(Eoo));
				fileexchange.setKeyfordoc(fileKey);
				verifyfile.Stroage(fileexchange);
				verifyfile.storageDoc(fileKey,doc);
				verifyfile.SetEoo(sender,fileid);
				return fileid;

			}

		}
		return null;
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
