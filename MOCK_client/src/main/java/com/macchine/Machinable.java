package com.macchine;

/**
 * definisce i metodi che una macchina (simulata deve avere per poter interagire con il server
 *
 */
public interface Machinable 
{
	public void caricaSuServer();
	public void aggiornaMacchina();
	public String getStato();
}
