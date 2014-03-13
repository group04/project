package com.groupproject.data;

/*
 * Copyright 2010-2014 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;


public class FileManager {

    private static AWSCredentials credentials;
  
    private static  String bucketName="tds-files";
    private static AmazonS3 s3 = new AmazonS3Client(credentials = new ClasspathPropertiesFileCredentialsProvider().getCredentials());
    private static TransferManager tx =new TransferManager(s3);
    private static Region usWest2 = Region.getRegion(Regions.US_WEST_2);

    public static void uploadFile(byte[] bFile,String key) throws IOException{

   
    	 s3.setRegion(usWest2);
         File file = File.createTempFile("temp-file", ".txt");
         file.deleteOnExit();
         Writer writer = new OutputStreamWriter(new FileOutputStream(file));
         FileOutputStream fileOuputStream =  new FileOutputStream(file); 
         fileOuputStream.write(bFile);
         writer.close();
         fileOuputStream.close();
         System.out.println("Uploading a new object to S3 from a file\n");
   
         try {
         s3.putObject(new PutObjectRequest(bucketName, key, file));
      
         }
         catch (AmazonServiceException ase) {
             System.out.println("Caught an AmazonServiceException, which means your request made it "
                     + "to Amazon S3, but was rejected with an error response for some reason.");
             System.out.println("Error Message:    " + ase.getMessage());
             System.out.println("HTTP Status Code: " + ase.getStatusCode());
             System.out.println("AWS Error Code:   " + ase.getErrorCode());
             System.out.println("Error Type:       " + ase.getErrorType());
             System.out.println("Request ID:       " + ase.getRequestId());
         } catch (AmazonClientException ace) {
             System.out.println("Caught an AmazonClientException, which means the client encountered "
                     + "a serious internal problem while trying to communicate with S3, "
                     + "such as not being able to access the network.");
             System.out.println("Error Message: " + ace.getMessage());
         }
    
        }

    		
    
    public  File downloadFile(String key) throws IOException{
    	s3.setRegion(usWest2);
    	File file = File.createTempFile("temp-file", ".txt");
     
   
        try {
        s3.getObject(new GetObjectRequest( bucketName,key),file);
        }
        catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
   
        return file;
       
   	
   	 }
   
  
    private void createAmazonS3Bucket() {
        try {
            if (tx.getAmazonS3Client().doesBucketExist(bucketName) == false) {
                tx.getAmazonS3Client().createBucket(bucketName);
            }
        } catch (AmazonClientException ace) {
           
        }
    }


    
}
