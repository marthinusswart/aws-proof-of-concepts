package com.mattswart.springboot.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.mattswart.springboot.dto.GDPDetailRecord;
import com.mattswart.springboot.util.SimpleJsonManager;

@Service
public class CountryGDPMessageSubscriber {
    @KafkaListener(topics = "gdp_detail_record", groupId = "aws-poc-group-1")
    public void consumeEvents(GDPDetailRecord gdpDetailRecord) {
        try {
            processGDPDetailRecord(gdpDetailRecord);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private void processGDPDetailRecord(GDPDetailRecord gdpDetailRecord) throws Exception {
        var simpleJsonManager = SimpleJsonManager.builder()
                .jsonFilePath("/home/matt/gdp_kafka_topic_data.json")
                .build();

        simpleJsonManager.append(gdpDetailRecord);

    }
}
