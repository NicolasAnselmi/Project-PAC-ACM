package com.macchine;

import org.springframework.web.client.RestTemplate;
/**
 * particolare implementazione della macchina
 *
 */
public class Tornio extends MacchinaFisica {
	private static int numTorni = 1;

	public Tornio(float probGuasto, float probFineMateriali, RestTemplate restTemplate, int s, int w) {
		super(probGuasto, probFineMateriali, "TORNIO-" + numTorni, restTemplate, s, w);
		numTorni++;
	}
}
