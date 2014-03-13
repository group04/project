package com.groupproject.data;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.simpledb.AmazonSimpleDB;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.Attribute;
import com.amazonaws.services.simpledb.model.BatchPutAttributesRequest;
import com.amazonaws.services.simpledb.model.CreateDomainRequest;
import com.amazonaws.services.simpledb.model.Item;
import com.amazonaws.services.simpledb.model.PutAttributesRequest;
import com.amazonaws.services.simpledb.model.ReplaceableAttribute;
import com.amazonaws.services.simpledb.model.ReplaceableItem;
import com.amazonaws.services.simpledb.model.SelectRequest;
import com.groupproject.model.File;


public class FileDBRepository {
	private static AWSCredentials credentials;
	private static AmazonSimpleDB sdb = new AmazonSimpleDBClient(credentials = new ClasspathPropertiesFileCredentialsProvider().getCredentials());
	private static String  myDomain= "TDSfiles" ;

	public static void startup(){
		myDomain= "TDSfiles";
		sdb = new AmazonSimpleDBClient(credentials = new ClasspathPropertiesFileCredentialsProvider().getCredentials());
	}
	/**
	 * Method to store file meta data in the SimplDB
	 * @param file ,the file data to be stored in database 
	 */

	public static void save(File file ){

		
		List<ReplaceableItem> fileData = new ArrayList<ReplaceableItem>();

		fileData.add(new ReplaceableItem(file.getFileID()).withAttributes(
				new ReplaceableAttribute("File_ID",file.getFileID(), true),
				new ReplaceableAttribute("Key_forDoc",file.getKeyfordoc(), true),
				new ReplaceableAttribute("File_Name",file.getFilename(), true),
				new ReplaceableAttribute("Client_Receiver",file.getClientRecever(), true),
				new ReplaceableAttribute("Client_Sender", file.getClientSender(), true),
				new ReplaceableAttribute("EOO", file.getEoo(), true),
				new ReplaceableAttribute("EOR", file.getEor(), true),
				new ReplaceableAttribute("State", file.getState(), true)
				));

		try {
			// Create a domain if doesn't exist 

			sdb.createDomain(new CreateDomainRequest(myDomain));

			// Put data into a domain

			sdb.batchPutAttributes(new BatchPutAttributesRequest(myDomain,fileData ));
			System.out.println("File Data Saved");

		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which means your request made it "
					+ "to Amazon SimpleDB, but was rejected with an error response for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which means the client encountered "
					+ "a serious internal problem while trying to communicate with SimpleDB, "
					+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}

	}

	/**
	 * delete the exchange 
	 * not required files shouldnt be deleted 
	 */
	public static void deleteExchange(String fileid){

	}
	/**
	 * Get the attribute value by attribute passing name 
	 * General method is used instead of using separate get method for each attribute 
	 *  @param keyForDoc the file key which is unique key for the file record 
	 *  @param attrbName Attribute name 
	 *  @return Returns the <code>value  <code>,value of the attribute
	 */
	public static String getAttrbValue(String fileId,String attrbName ){
		
		String selectExpression = "select`"+ attrbName + "` from `" + myDomain + "` where File_ID ="+"'"+fileId+"'";
		String value=null ;
       
		SelectRequest selectRequest = new SelectRequest(selectExpression);
		for (Item item : sdb.select(selectRequest).getItems()) {
		
			for (Attribute attribute : item.getAttributes()) {
				value=attribute.getValue();

			}
		}
		return value;

	}
	public String getEOO(String fileID){
		return getAttrbValue(fileID, "EOO");	
	}

	/**
	 * Update attribute value by attribute passing name 
	 * General method is used instead of using separate get method for each attribute 
	 *  @param keyForDoc the file key which is unique key for the file record 
	 *  @param attrbName Attribute name which need to be updated 
	 *  @param value ,value to be updated 
	 */

	public  static void updateAttrb(String fileId,String attrbName,String value){

		List<ReplaceableAttribute> replaceableAttributes = new ArrayList<ReplaceableAttribute>();
		replaceableAttributes.add(new ReplaceableAttribute(attrbName, value, true));
		sdb.putAttributes(new PutAttributesRequest(myDomain,fileId, replaceableAttributes));

	}
	public String getReceiver(String fileID) {
		// TODO Auto-generated method stub
		return getAttrbValue(fileID, "Client_Receiver");
	}
	public String getName(String fileID) {
		// TODO Auto-generated method stub
		return getAttrbValue(fileID, "File_Name");
	}
	public String getSender(String fileID) {
		// TODO Auto-generated method stub
		return getAttrbValue(fileID, "Client_Sender");
	}
	public String getfileeor(String fileID) {
		// TODO Auto-generated method stub
		return getAttrbValue(fileID, "EOR");
	}
	public String getdocKey(String fileID) {
		// TODO Auto-generated method stub
		return getAttrbValue(fileID, "Key_forDoc");
	}
	public void updateEor(String fileid, String eor) {
		updateAttrb(fileid,"EOR",eor);
		
	}


}
