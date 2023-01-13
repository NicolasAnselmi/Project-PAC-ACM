package com.accessori;

public class Lavorazione 
{
	private int timestampInizio;
	private int timestampFine;
	private int codiceMacchina;
	private int codiceLotto;
	
	//autogenerato
	public Lavorazione(int timestampInizio, int timestampFine, int codiceMacchina, int codiceLotto) {
		super();
		this.timestampInizio = timestampInizio;
		this.timestampFine = timestampFine;
		this.codiceMacchina = codiceMacchina;
		this.codiceLotto = codiceLotto;
	}
	
	public Lavorazione(String json)
	{
		//todo: ricevuta in json deve essere scompattata
	}
	
	//autogenerato
	public int getTimestampInizio() {
		return timestampInizio;
	}
	public int getTimestampFine() {
		return timestampFine;
	}
	public int getCodiceMacchina() {
		return codiceMacchina;
	}
	public int getCodiceLotto() {
		return codiceLotto;
	}		
}
