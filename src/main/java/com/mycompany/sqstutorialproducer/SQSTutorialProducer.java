
package com.mycompany.sqstutorialproducer;

/**
 *
 * @author Ali
 */

import javax.annotation.PreDestroy;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;
public class SQSTutorialProducer {
SqsClient sqsClient;
  String queueUrl;
	
  public SQSTutorialProducer(String key, String secretKey, String queueUrl) {
    AwsBasicCredentials awsCreds = AwsBasicCredentials.create(key, secretKey);

    this.sqsClient = SqsClient.builder()
      .credentialsProvider(StaticCredentialsProvider
      .create(awsCreds)).region(Region.AP_SOUTH_1).build();

    this.queueUrl = queueUrl;
  }
  public String sendMessage(String message) {
    SendMessageRequest request = SendMessageRequest.builder()
      .queueUrl(this.queueUrl).messageBody(message)
      .delaySeconds(5).build();		

    SendMessageResponse response = this.sqsClient.sendMessage(request);
    return response.messageId();
  }
   @PreDestroy
  public void preDestroy() {
    this.sqsClient.close();
  }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("Station running....");
         //put your credential key ,secret key, and sqs queue name 
         //put your credential key ,secret key, and sqs queue name 
                //Access Key ID:
                //Secret Access Key:
                // SQS queue name 
    SQSTutorialProducer station = new SQSTutorialProducer("",
      "",
      "");
     String id = station.sendMessage("Hiiii");
    System.out.println("sent message: " + id);
    
    id = station.sendMessage("Hello");
    System.out.println("sent message: " + id);
   
    id = station.sendMessage("This is JMS");
    System.out.println("sent message: " + id);
    }

 }

