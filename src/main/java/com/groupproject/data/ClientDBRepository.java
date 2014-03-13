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
import com.groupproject.model.Client;


public class ClientDBRepository {
	
	private static AWSCredentials credentials;
	private static AmazonSimpleDB sdb = new AmazonSimpleDBClient(credentials = new ClasspathPropertiesFileCredentialsProvider().getCredentials());
	private static String  myDomain= "TDS_Clients" ;


	/**
	 * Method to store client  data in the SimplDB
	 * @param client,client object to be stored in database 
	 */

	public  void save(Client client ){

		List<ReplaceableItem> clientData = new ArrayList<ReplaceableItem>();


		clientData.add(new ReplaceableItem(client.getClientID()).withAttributes(
				new ReplaceableAttribute("Client_Id",client.getClientID(), true),
				//new ReplaceableAttribute("Nummessage",client.getNummessage(), true),
				new ReplaceableAttribute("Public_Key", client.getPublicKey(), true)
				));

		try {
			// Create a domain if doesn't exist 

			sdb.createDomain(new CreateDomainRequest(myDomain));

			// Put data into a domain

			sdb.batchPutAttributes(new BatchPutAttributesRequest(myDomain,clientData ));


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
	 * Get the attribute value by attribute passing name 
	 * General method is used instead of using separate get method for each attribute 
	 *  @param  clientId the file key which is unique key for the file record 
	 *  @param attrbName Attribute name 
	 *  @return Returns the <code>value  <code>,value of the attribute
	 */
	public static String getAttrbValue(String clientId,String attrbName ){

		String selectExpression = "select`"+ attrbName + "` from `" + myDomain + "` where Client_Id ="+"'"+clientId+"'";
		String value=null ;

		SelectRequest selectRequest = new SelectRequest(selectExpression);
		for (Item item : sdb.select(selectRequest).getItems()) {

			for (Attribute attribute : item.getAttributes()) {
				value=attribute.getValue();

			}
		}
		return value;

	}

	/**
	 * Update attribute value by attribute passing name 
	 * General method is used instead of using separate get method for each attribute 
	 *  @param clientId the file key which is unique key for the file record 
	 *  @param attrbName Attribute name which need to be updated 
	 *  @param value ,value to be updated 
	 */

	public  static void updateAttrb(String clientId,String attrbName,String value){

		List<ReplaceableAttribute> replaceableAttributes = new ArrayList<ReplaceableAttribute>();
		replaceableAttributes.add(new ReplaceableAttribute(attrbName, value, true));
		sdb.putAttributes(new PutAttributesRequest(myDomain,clientId, replaceableAttributes));

	}


	public boolean SearchClient(String sender) {
		
		String id = getAttrbValue(sender, "Client_Id");
		return id != null;
	}


	public String getPublicKey(String clientID) {
		return getAttrbValue(clientID, "Public_Key");		
	}












}
