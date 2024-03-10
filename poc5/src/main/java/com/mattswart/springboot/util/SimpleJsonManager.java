package com.mattswart.springboot.util;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.mattswart.springboot.dto.GDPDetailRecord;

public class SimpleJsonManager {
    private String jsonFilePath;
    
    public static Builder builder(){
        return new Builder();
    }

    public SimpleJsonManager() {
    }

    private SimpleJsonManager(Builder builder) {
        this.jsonFilePath = builder.jsonFilePath;
    }    

    public void append(GDPDetailRecord gdpDetailRecord) throws Exception{
        File jsonFile = new File(jsonFilePath);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        
        if (!jsonFile.exists()) {            
            jsonFile.createNewFile();
        } else if ((jsonFile.length() / 1000) > 50) {
            var path = Paths.get(jsonFilePath);
            var parentPath = path.getParent() ;
            var filename = path.getFileName() + "." + LocalDateTime.now() + ".json";
            filename = parentPath + "/" + filename;
            var archiveFile = new File(filename);
            jsonFile.renameTo(archiveFile);
            jsonFile.createNewFile();
        } 

        List<GDPDetailRecord> gdpDetailRecords = null;

        try{
            gdpDetailRecords = objectMapper.readValue(jsonFile, new TypeReference<List<GDPDetailRecord>>() {});
        } catch (MismatchedInputException ex){
            gdpDetailRecords = new ArrayList<GDPDetailRecord>();
        }
        
        gdpDetailRecords.add(gdpDetailRecord);        
        writer.writeValue(jsonFile, gdpDetailRecords);       
    }

    public static class Builder {

        private String jsonFilePath;

        public Builder() {
        }

        public Builder jsonFilePath(String jsonFilePath) {
            this.jsonFilePath = jsonFilePath;
            return this;
        }

        public SimpleJsonManager build() {
            return new SimpleJsonManager(this);
        }
    }
}
