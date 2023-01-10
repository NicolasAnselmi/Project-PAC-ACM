package com.macchine;

public class Fresa extends Macchina
{
	private static int numFrese = 1;
	
	public Fresa(float probGuasto, float probFineMateriali)
	{
		super(probGuasto, probFineMateriali, "FRESA-"+numFrese);
		numFrese++;
	}
}

