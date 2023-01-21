package com.macchine;

import java.util.concurrent.Semaphore;

import org.springframework.web.client.RestTemplate;
/**
 * particolare implementazione della macchina
 *
 */
public class Tornio extends MacchinaFisica {
	private static int numTorni = 1;

	public Tornio(float probGuasto, float probFineMateriali, int waitTime, RestTemplate restTemplate, Semaphore s) {
		super(probGuasto, probFineMateriali, "TORNIO-" + numTorni, "tornio", waitTime, restTemplate, s);
		numTorni++;
	}
}
