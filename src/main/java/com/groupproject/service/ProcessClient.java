package com.groupproject.service;


import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import com.groupproject.data.ClientServer;
import com.groupproject.model.Client;


public class ProcessClient {
	@Inject
	private ClientServer clientserver;
	
	
	/**
	 * A method verify the given clientid if the clientid has already be used
	 *  if exist return true or return false. 
	 */
	public Boolean verify(String id){
		
		return clientserver.SearchClient(id);
		
	}
	
	
	
	/**
	 * a method will generate the public key and private key for the clientid
	 * return a String[](public key and private key)
	 * @throws NoSuchAlgorithmException 
	 */
	public String generateKey(String id){
		GenerateKeyPair gene = null;
		try {
			gene = new GenerateKeyPair(id);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Client client=new Client();
		client.setClientID(id);
		String pukey=CodeExchange.getString(gene.getPublickey());
		String prkey=CodeExchange.getString(gene.getPrivatekey());
		client.setPublicKey(pukey);
		client.setPrivateKey(prkey);
		storage(client);
		return prkey;
		
	}
	
	
	
	/**
	 * a method will storage the client inforamtion in the database
	 * 
	 */	
	public void storage(Client client){
		clientserver.storageClient(client);
		
	}

}
