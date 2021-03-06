package com.groupproject.model;

/**
 * @author hp
 *
 */
public class File {
	private String fileID;
	private String clientSender;
	private String clientRecever;
	private String keyfordoc;
	private String Eoo;
	private String Eor;
	private String filename;
	private String state;
	private final int NEW=0;//the directory donot storage any file
	private final int INCOMPLETE=1;//the directory storage the doc file or EOO one file
	private final int COMPLETE=2;//the directory storage doc and the EOO two file
	private final int SUCCESSFUL=3;//the directory storage three file EOR EOO doc
	private final int ABORT=-1;

	public File(){
		this.Eoo="NA";
		this.Eor="NA";
		this.state="new";
		this.keyfordoc=keyfordoc;
		this.filename=keyfordoc;
		this.clientRecever="NA";
		this.clientSender="NA";
	}
	public String getKeyfordoc() {
		return keyfordoc;
	}
	public void setKeyfordoc(String keyfordoc) {
		this.keyfordoc = keyfordoc;
	}
	
	public String getEoo() {
		return Eoo;
	}
	public void setEoo(String eoo) {
		Eoo = eoo;
	}
	public String getEor() {
		return Eor;
	}
	public void setEor(String eor) {
		Eor = eor;
	}
	public String getFileID() {
		return fileID;
	}
	public void setFileID(String fileID) {
		this.fileID = fileID;
	}
	public String getClientSender() {
		return clientSender;
	}
	public void setClientSender(String clientSender) {
		this.clientSender = clientSender;
	}
	public String getClientRecever() {
		return clientRecever;
	}
	public void setClientRecever(String clientRecever) {
		this.clientRecever = clientRecever;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	

}
