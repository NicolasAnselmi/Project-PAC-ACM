package com.macchine;

public class ThreadMacchina extends Thread {
	private Machinable machine;
	static int quant;

	public ThreadMacchina(int quant, Machinable machine) {
		this.quant = quant;
		this.machine = machine;
	}

	/**
	 * Un thread simula il comportamento di una singola macchina: prima la inizializza,
	 * poi ad ogni iterazione (ogni quant secondi) aggiorna lo stato della macchina e lo
	 * carica sul server
	 */
	public void run() {
		machine.inizializzaMacchina();

		while(true)
		{
		try {
			Thread.sleep(quant*1000);
			} catch (InterruptedException e) {
			System.err.println("errore " + e.toString());
		}
		machine.aggiornaMacchina();
		System.out.println(machine.getStato());
		machine.caricaSuServer();
		}
	}

}
