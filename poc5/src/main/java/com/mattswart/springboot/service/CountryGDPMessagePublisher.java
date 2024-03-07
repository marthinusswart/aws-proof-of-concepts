package com.mattswart.springboot.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.mattswart.springboot.dto.GDPDetailRecord;

@Service
public class CountryGDPMessagePublisher {
    @Autowired
    private KafkaTemplate<String,Object> template;

    public void sendGDPDetailRecordToTopic(GDPDetailRecord gdpDetailRecord) {
        try {
            CompletableFuture<SendResult<String, Object>> future = template.send("gdp_detail_record", gdpDetailRecord);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Sent message=[" + gdpDetailRecord.toString() +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send message=[" +
                    gdpDetailRecord.toString() + "] due to : " + ex.getMessage());
                }
            });

        } catch (Exception ex) {
            System.out.println("ERROR : "+ ex.getMessage());
        }
    }
}
