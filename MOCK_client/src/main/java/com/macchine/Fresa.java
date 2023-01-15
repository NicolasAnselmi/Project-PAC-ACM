package com.macchine;

import org.springframework.web.client.RestTemplate;

/**
 * particolare implementazione della macchina
 *
 */
public class Fresa extends MacchinaFisica {
	private static int numFrese = 1;

	public Fresa(float probGuasto, float probFineMateriali, RestTemplate restTemplate, int s, int w) {
		super(probGuasto, probFineMateriali, "FRESA-" + numFrese,restTemplate, s, w);
		numFrese++;
	}
}
