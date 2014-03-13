package com.groupproject.model;

public class Client {
	private String clientID;
	private String publicKey;

	private String nummessage;
	
	public Client(){
		
	}
	public String getNummessage() {
		return nummessage;
	}
	public void setNummessage(String nummessage) {
		this.nummessage = nummessage;
	}
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	
	

}
