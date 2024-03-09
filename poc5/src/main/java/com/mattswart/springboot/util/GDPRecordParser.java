package com.mattswart.springboot.util;

import com.mattswart.springboot.dto.GDPDetailRecord;

public class GDPRecordParser {

    private String csvFilePath;
    private SimpleCSVParser simpleCSVParser = new SimpleCSVParser();

    public GDPRecordParser() {
    }

    private GDPRecordParser(Builder builder) {
        this.csvFilePath = builder.csvFilePath;
    }

    public void initialiseFile() throws Exception{
        simpleCSVParser.initialiseFile(this.csvFilePath);        
    }

    public GDPDetailRecord nextRecord() throws Exception {
        String[] record = simpleCSVParser.readLine();
        if (record != null) {
            var gdpDetailRecord = new GDPDetailRecord(record[0], record[1], record[2], record[3], record[4], 
                    record[5], record[6], record[7], record[8], record[9], record[10], record[11], record[12], record[13], record[14],
                    record[15], record[16], record[17], record[18], record[19], record[20], record[21], record[22], record[23], record[24],
                    record[25], record[26], record[27], record[28], record[29], record[30], record[31], record[32], record[33], record[34],
                    record[35], record[36], record[37], record[38], record[39], record[40], record[41], record[42], record[43], record[44],
                    record[45], record[46], record[47], record[48], record[49], record[50], record[51], record[52], record[53], record[54],
                    record[55], record[56], record[57], record[58], record[59], record[60], record[61], record[62], record[63], record[64],
                    record[65], record[66]);

            return gdpDetailRecord;
        } else {
            return null;
        }
    }

    public static class Builder {

        private String csvFilePath;

        public Builder() {
        }

        public Builder csvFilePath(String csvFilePath) {
            this.csvFilePath = csvFilePath;
            return this;
        }

        public GDPRecordParser build() {
            return new GDPRecordParser(this);
        }
    }
}
