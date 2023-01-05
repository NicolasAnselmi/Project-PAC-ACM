package com.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.macchine.ServiceMacchina;

@RestController
public class MacchineWebController {
	
	@Autowired
	private ServiceMacchina serviceMacchine;

}
