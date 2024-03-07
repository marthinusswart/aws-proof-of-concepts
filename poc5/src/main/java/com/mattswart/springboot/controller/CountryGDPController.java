package com.mattswart.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.mattswart.springboot.dto.GDPServiceStatus;

@RestController
@RequestMapping("/gdp")
public class CountryGDPController {

    @GetMapping("/is_running")
	public GDPServiceStatus serviceStatus() {
		return new GDPServiceStatus("GDP Service", "Running");
	}

}
