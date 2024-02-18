package com.mattswart.aws;

import java.util.HashMap;

public class GlueJobStateChangeEvent {
    private String jobName = "";
    private String severity = "";
    private String state = "";
    private String jobRunId = "";
    private String message = "";

    public boolean parseEventHashMap(HashMap eventHashMap){
        HashMap detail = (HashMap) eventHashMap.get("detail");
        this.jobName = detail.get("jobName").toString();
        this.severity = detail.get("severity").toString();
        this.state = detail.get("state").toString();
        this.jobRunId = detail.get("jobRunId").toString();
        this.message = detail.get("message").toString();
        return true;        
    }

    public String getName(){
        return this.jobName;
    }

    public String getSeverity(){
        return this.severity;
    }
    public String getState(){
        return this.state;
    }
    public String getJobRunId(){
        return this.jobRunId;
    }
    public String getMessage(){
        return this.message;
    }

}
