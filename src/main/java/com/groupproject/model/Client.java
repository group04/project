package com.groupproject.model;

public class Client {
	private String clientID;
	private String publicKey;
	private String privateKey;
	private int nummessage;
	
	public Client(){
		this.nummessage=0;
	}
	public int getNummessage() {
		return nummessage;
	}
	public void setNummessage(int nummessage) {
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
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	

}
