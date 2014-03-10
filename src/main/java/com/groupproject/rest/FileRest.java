package com.groupproject.rest;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
//import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.groupproject.data.FileDBRepository;
import com.groupproject.model.File;
import com.groupproject.service.CodeExchange;
import com.groupproject.service.FileVerify;



@Path("/flie")
public class FileRest {
	@Inject
	private FileVerify verifyfile;
	@Inject
	private FileDBRepository fileexchangeserver;

	/**
	 * POST the clientidsender , clientidreveiver SigA(H(doc)) file,and the doc.
	 * (this area I suggest the resteasy to do it) this link is how to do it
	 * http://blog.csdn.net/jackyrongvip/article/details/6670500
	 */
	@POST
	public String upLoadclientID(){
		String receiver = null;
		String sender = null;
		byte[] Eoo = null;
		byte[] doc = null;
		String filename = null;

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
				verifyfile.SetEoo(fileid);
				return fileid;

			}

		}
		return null;
	}

}
