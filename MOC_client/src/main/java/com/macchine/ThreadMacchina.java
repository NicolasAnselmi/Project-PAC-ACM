package com.macchine;

public class ThreadMacchina implements Runnable {
	private Machinable machine;
	static int quant;

	public ThreadMacchina(int quant, Machinable machine) {
		this.quant = quant;
		this.machine = machine;
	}

	public void run() {
		machine.inizializzaMacchina();

		while (true) {
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
			}

			machine.aggiornaMacchina();
			machine.caricaSuServer();
		}
	}

}
