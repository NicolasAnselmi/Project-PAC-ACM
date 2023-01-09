package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.service.autenticazione.ServiceAutenticazione;

@RestController
public class AutenticazioneWebController {

	@Autowired
	private ServiceAutenticazione serviceAutenticazione;
}
