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

		try {
			Thread.sleep(300);
			System.out.println(machine.toString() + " run ");
		} catch (InterruptedException e) {
			System.err.println("errore " + e.toString());
		}

		System.out.println(machine.toString() + " finish ");
		machine.aggiornaMacchina();
		machine.caricaSuServer();
	}

}
