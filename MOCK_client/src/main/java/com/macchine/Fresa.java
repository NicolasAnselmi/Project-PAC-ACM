package com.macchine;

import java.util.concurrent.Semaphore;

import org.springframework.web.client.RestTemplate;

/**
 * particolare implementazione della macchina
 *
 */
public class Fresa extends MacchinaFisica {
	private static int numFrese = 1;

	public Fresa(float probGuasto, float probFineMateriali, int waitTime, RestTemplate restTemplate, Semaphore s) {
		super(probGuasto, probFineMateriali, "FRESA-" + numFrese,"fresa",waitTime, restTemplate, s);
		numFrese++;
	}

}
