package com.models.macchine;

import com.datamanager.LogDataManager;
import com.service.notifiche.GestoreNotifiche;

public class Macchina implements Machinable {
	private String codiceMacchina;
	private int codiceLottoInLavorazione;
	private String timeStampUltimoMessaggio;
	private StatoMacchina statoMacchina;

	private String json;

	private GestoreNotifiche gestoreNotifiche;

	public Macchina() {
		gestoreNotifiche = GestoreNotifiche.getGestoreNotifiche();
	}

	
	/**
	 * scompatta json, aggiunge log, DOVE AGGIUNGE LOG? manda solo notifiche mi sembra
	 */
	public void aggiornaMacchina(int codiceLottoInLavorazione, String timeStampMessaggio, StatoMacchina statoMacchina, String json) {
		this.json = json;
		this.codiceLottoInLavorazione = codiceLottoInLavorazione;
		this.timeStampUltimoMessaggio = timeStampMessaggio;
		if (!this.statoMacchina.equals(statoMacchina)) {
			String body, title;
			if (statoMacchina.equals(StatoMacchina.AttesaMateriale)) {
				body = this.codiceMacchina + " ha terminato il materiale e la produzione si è fermata";
				title = this.codiceMacchina + " FINE MATERIALE";
			} else if (statoMacchina.equals(StatoMacchina.Guasta)) {
				body = this.codiceMacchina + " si è guastata e la produzione si è fermata";
				title = this.codiceMacchina + " GUASTA";
			} else if (statoMacchina.equals(StatoMacchina.Fermo)) {
				body = this.codiceMacchina + " ha terminato la lavorazione";
				title = this.codiceMacchina + " LAVORAZIONE TERMINATA";
			} else {
				body = this.codiceMacchina + " ha ripreso la lavorazione";
				title = this.codiceMacchina + " LAVORAZIONE RIPRESA";
			}
			gestoreNotifiche.sendOperai(body, title);
			this.statoMacchina = statoMacchina;
		}
	}

	@Override
	public String getInfoMacchina() {
		return json;
	}


	public String getCodiceMacchina() {
		return codiceMacchina;
	}
	
	
}
