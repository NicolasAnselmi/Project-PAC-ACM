package com.macchine;

import org.springframework.web.client.RestTemplate;

public class Tornio extends Macchina {
	private static int numTorni = 1;

	public Tornio(float probGuasto, float probFineMateriali, RestTemplate restTemplate) {
		super(probGuasto, probFineMateriali, "TORNIO-" + numTorni, restTemplate);
		numTorni++;
	}
}
