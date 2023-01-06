package com.models.macchine;

public interface Machinable 
{
	void aggiornaMacchina(int codiceLotto, int timeStampMessaggio, StatoMacchina statoMacchina, String json);
	public String getInfoMacchina(String json);
}
