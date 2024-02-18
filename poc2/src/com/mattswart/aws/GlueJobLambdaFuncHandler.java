package com.mattswart.aws;

import java.util.HashMap;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

//import com.amazonaws.services.lambda.runtime.events.SQSEvent;
//import com.amazonaws.services.lambda.runtime.events.;

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

          return "Ok";
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
    }

}
