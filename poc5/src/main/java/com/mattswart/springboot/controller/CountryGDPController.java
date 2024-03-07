package com.mattswart.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mattswart.springboot.service.CountryGDPMessagePublisher;
import com.mattswart.springboot.dto.GDPDetailRecord;

import com.mattswart.springboot.dto.GDPServiceStatus;

@RestController
@RequestMapping("/gdp")
public class CountryGDPController {
	@Autowired
	private CountryGDPMessagePublisher publisher;

	@GetMapping("/is_running")
	public GDPServiceStatus serviceStatus() {
		return new GDPServiceStatus("GDP Service", "Running");
	}

	@GetMapping("/simulate_process_file")
	public GDPDetailRecord simulateProcessFile() {
		try {
			var gdpDetailRecord = new GDPDetailRecord("Australia");
			//publisher.sendGDPDetailRecordToTopic(gdpDetailRecord);
			return gdpDetailRecord;
		} catch (Exception ex) {
			return new GDPDetailRecord("Failed to process");
		}
	}

}
