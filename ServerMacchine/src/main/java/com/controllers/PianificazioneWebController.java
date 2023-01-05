package com.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.pianificazione.ServicePianificazione;

@RestController
public class PianificazioneWebController {
	
	@Autowired
	private ServicePianificazione servicePianificazione;
	
	

}
