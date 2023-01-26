package com.models.macchine;

import com.google.cloud.Timestamp;
import com.notifiche.GestoreNotifiche;

public class Macchina implements Machinable {
	private String idMacchina;
	private String codiceLottoInLavorazione;
	private String timeStampUltimoMessaggio;
	private StatoMacchina statoMacchina;
	private TipoMacchina tipoMacchina;

	private GestoreNotifiche gestoreNotifiche;

	private Macchina() {
		gestoreNotifiche = GestoreNotifiche.getGestoreNotifiche();
	}

	public Macchina(String idMacchina, String tipoMacchina) {
		this();
		this.idMacchina = idMacchina;
		this.codiceLottoInLavorazione = "";
		this.timeStampUltimoMessaggio = Timestamp.now().toString();
		this.statoMacchina = StatoMacchina.Fermo;
		this.tipoMacchina = TipoMacchina.valueOf(tipoMacchina);
	}

	/**
	 * scompatta json, aggiunge log, DOVE AGGIUNGE LOG? manda solo notifiche mi
	 * sembra
	 */
	public void aggiornaMacchina(String codiceLottoInLavorazione, String timeStampMessaggio, StatoMacchina statoMacchina) {
		this.codiceLottoInLavorazione = codiceLottoInLavorazione;
		this.timeStampUltimoMessaggio = timeStampMessaggio;
		if (!this.statoMacchina.equals(statoMacchina)) {
			String body, title;
			if (statoMacchina.equals(StatoMacchina.AttesaMateriale)) {
				body = this.idMacchina + " ha terminato il materiale e la produzione si è fermata";
				title = this.idMacchina + " FINE MATERIALE";
			} else if (statoMacchina.equals(StatoMacchina.Guasta)) {
				body = this.idMacchina + " si è guastata e la produzione si è fermata";
				title = this.idMacchina + " GUASTA";
			} else if (statoMacchina.equals(StatoMacchina.Fermo)) {
				body = this.idMacchina + " ha terminato la lavorazione";
				title = this.idMacchina + " LAVORAZIONE TERMINATA";
			} else {
				body = this.idMacchina + " ha ripreso la lavorazione";
				title = this.idMacchina + " LAVORAZIONE RIPRESA";
			}
			gestoreNotifiche.sendOperai(body, title);
			this.statoMacchina = statoMacchina;
		}
	}

	public String getCodiceLottoInLavorazione() {
		return codiceLottoInLavorazione;
	}

	public void setCodiceLottoInLavorazione(String codiceLottoInLavorazione) {
		this.codiceLottoInLavorazione = codiceLottoInLavorazione;
	}

	public StatoMacchina getStatoMacchina() {
		return statoMacchina;
	}

	public String getidMacchina() {
		return idMacchina;
	}

	public String getTimeStampUltimoMessaggio() {
		return timeStampUltimoMessaggio;
	}
	
	public TipoMacchina getTipoMacchina()
	{
		return tipoMacchina;
	}

}
