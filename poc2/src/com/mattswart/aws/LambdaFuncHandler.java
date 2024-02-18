package com.mattswart.aws;

import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.S3Client;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.S3EventNotificationRecord;

public class LambdaFuncHandler implements RequestHandler<S3Event, String> {
    
    @Override
    public String handleRequest(S3Event s3event, Context context) {
        try {
          S3EventNotificationRecord record = s3event.getRecords().get(0);
          String srcBucket = record.getS3().getBucket().getName();
          String srcKey = record.getS3().getObject().getUrlDecodedKey();

          S3Client s3Client = S3Client.builder().build();
          HeadObjectResponse headObject = getHeadObject(s3Client, srcBucket, srcKey);

          LambdaLogger logger = context.getLogger();
          logger.log("Successfully retrieved " + srcBucket + "/" + srcKey + " of type " + headObject.contentType());

          // check that it is the world-bank-open-data/GC.DOD.TOTL.GD.ZS/ location
          if (srcKey.contains("world-bank-open-data/GC.DOD.TOTL.GD.ZS/")){
            logger.log("The uploaded file is a part of the World Bank indicator GC.DOD.TOTL.GD.ZS - " + srcBucket + "/" + srcKey + " of type " + headObject.contentType());            
          }

          return "Ok";
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
    }

    private HeadObjectResponse getHeadObject(S3Client s3Client, String bucket, String key) {
        HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();
        return s3Client.headObject(headObjectRequest);
    }        
}
