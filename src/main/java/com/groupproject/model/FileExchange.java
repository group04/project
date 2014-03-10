package com.groupproject.model;

public class FileExchange {
	private String fileID;
	private String clientSender;
	private String clientRecever;
	private String fileURL;
	private String filename;
	private int state;
	private final int NEW=0;//the directory donot storage any file
	private final int INCOMPLETE=1;//the directory storage the doc file or EOO one file
	private final int COMPLETE=2;//the directory storage doc and the EOO two file
	private final int SUCCESSFUL=3;//the directory storage three file EOR EOO doc
	private final int ABORT=-1;

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
	public String getFileURL() {
		return fileURL;
	}
	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	

}
