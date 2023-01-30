package com.models.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutenticazioneWebController {

	@SuppressWarnings("unused")
	@Autowired
	private ServiceAutenticazione serviceAutenticazione;
}
