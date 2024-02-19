package com.mattswart.aws;

import java.util.HashMap;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.S3Object;
import java.util.List;

public class GlueJobLambdaFuncHandler implements RequestHandler<HashMap, String> {
    @Override
    public String handleRequest(HashMap event, Context context) {
        try {
          LambdaLogger logger = context.getLogger();

            GlueJobStateChangeEvent glueJobEvent = new GlueJobStateChangeEvent();
            glueJobEvent.parseEventHashMap(event);

          logger.log("Successfully retrieved " + glueJobEvent.toString());
          logger.log("Job name " + glueJobEvent.getName());
          logger.log("Job state " + glueJobEvent.getState());
          logger.log("Job RunId " + glueJobEvent.getJobRunId());

          deleteImportFileFromStaging(logger);

          return "Ok";
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
    }

    private void deleteImportFileFromStaging(LambdaLogger logger){
      S3Client s3 = S3Client.builder().region(Region.AP_SOUTHEAST_2).build();

      ListObjectsRequest listObjectsRequest = ListObjectsRequest.builder().bucket("poc-staging").prefix("world-bank-open-data/GC.DOD.TOTL.GD.ZS/").delimiter("/").build();

      ListObjectsResponse res = s3.listObjects(listObjectsRequest);
      List<S3Object> objects = res.contents();
            for (S3Object myValue : objects) {
              logger.log("The name of the key is " + myValue.key());
              logger.log("The object is " + myValue.size() + " bytes");
              logger.log("The owner is " + myValue.owner());

              if (myValue.key().endsWith("csv")){
                logger.log("Deleting the import file " + myValue.key());
                var deleteObjectRequest = DeleteObjectRequest.builder().bucket("poc-staging").key(myValue.key()).build();
                s3.deleteObject(deleteObjectRequest);
              }
            }   

      s3.close();
    }

}
