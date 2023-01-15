package com.macchine;

public class ThreadMacchina implements Runnable {
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
			System.out.println(machine.toString() + " run ");
			} catch (InterruptedException e) {
			System.err.println("errore " + e.toString());
		}

		System.out.println(machine.toString() + " finish ");
		machine.aggiornaMacchina();
		machine.caricaSuServer();
		}
	}

}
