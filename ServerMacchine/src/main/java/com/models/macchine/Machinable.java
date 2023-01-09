package com.models.macchine;

public interface Machinable 
{
	void aggiornaMacchina(int codiceLotto, String timeStampMessaggio, StatoMacchina statoMacchina, String json);
	public String getInfoMacchina();
}
