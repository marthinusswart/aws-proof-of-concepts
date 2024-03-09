package com.mattswart.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mattswart.springboot.service.CountryGDPMessagePublisher;
import com.mattswart.springboot.util.GDPRecordParser;
import com.mattswart.springboot.dto.GDPDetailRecord;

import com.mattswart.springboot.dto.GDPServiceStatus;
import org.springframework.web.bind.annotation.RequestParam;

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
			var gdpDetailRecord = new GDPDetailRecord("Australia", "AUS", "GDP", "GDP", "1.0", 
			"1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", 
			"1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", 
			"1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0",
			"1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0",
			"1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0",
			"1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0",
			"1.0", "1.0");
			publisher.sendGDPDetailRecordToTopic(gdpDetailRecord);
			return gdpDetailRecord;
		} catch (Exception ex) {
			return new GDPDetailRecord("Failed process", "", "", "GDP", "1.0", 
			"1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", 
			"1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", 
			"1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0",
			"1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0",
			"1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0",
			"1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0", "1.0",
			"1.0", "1.0");
		}
	}

	@GetMapping("/process_file")
	public String processFile(@RequestParam String filepath) {
		try {
			var gdpRecordParser = new GDPRecordParser.Builder()
					.csvFilePath(filepath)
					.build();
			gdpRecordParser.initialiseFile();
			var hasMoreRecords = true;

			while (hasMoreRecords) {
				var gdpDetailRecord = gdpRecordParser.nextRecord();
				if (gdpDetailRecord != null) {
					System.out.println(gdpDetailRecord);
					publisher.sendGDPDetailRecordToTopic(gdpDetailRecord);
				} else {
					hasMoreRecords = false;
				}
			}

			return "Processed";
		} catch (Exception ex) {
			return "Failed to process. " + ex.toString();
		}
	}

}
