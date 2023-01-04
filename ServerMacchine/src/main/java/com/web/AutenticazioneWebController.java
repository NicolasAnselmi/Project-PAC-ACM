package com.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.autenticazione.ServiceAutenticazione;

@RestController
public class AutenticazioneWebController {

	@Autowired
	private ServiceAutenticazione serviceAutenticazione;
}
