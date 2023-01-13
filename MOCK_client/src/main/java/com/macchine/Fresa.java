package com.macchine;

import org.springframework.web.client.RestTemplate;

public class Fresa extends MacchinaFisica {
	private static int numFrese = 1;

	public Fresa(float probGuasto, float probFineMateriali, RestTemplate restTemplate) {
		super(probGuasto, probFineMateriali, "FRESA-" + numFrese,restTemplate);
		numFrese++;
	}
}
