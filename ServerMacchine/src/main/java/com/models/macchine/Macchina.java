package com.models.macchine;

import com.google.cloud.Timestamp;
import com.notifiche.GestoreNotifiche;

public class Macchina implements Machinable {
	private String idMacchina;
	private int codiceLottoInLavorazione;
	private String timeStampUltimoMessaggio;
	private StatoMacchina statoMacchina;
	private TipoMacchina tipoMacchina;

	private String json;

	private GestoreNotifiche gestoreNotifiche;

	private Macchina() {
		gestoreNotifiche = GestoreNotifiche.getGestoreNotifiche();
	}

	public Macchina(String idMacchina, TipoMacchina tipoMacchina) {
		this();
		this.idMacchina = idMacchina;
		this.codiceLottoInLavorazione = 0;
		this.timeStampUltimoMessaggio = Timestamp.now().toString();
		this.statoMacchina = StatoMacchina.Fermo;
		this.tipoMacchina = tipoMacchina;
	}

	/**
	 * scompatta json, aggiunge log, DOVE AGGIUNGE LOG? manda solo notifiche mi
	 * sembra
	 */
	public void aggiornaMacchina(int codiceLottoInLavorazione, String timeStampMessaggio, StatoMacchina statoMacchina,
			String json) {
		this.json = json;
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

	public int getCodiceLottoInLavorazione() {
		return codiceLottoInLavorazione;
	}

	public void setCodiceLottoInLavorazione(int codiceLottoInLavorazione) {
		this.codiceLottoInLavorazione = codiceLottoInLavorazione;
	}

	public StatoMacchina getStatoMacchina() {
		return statoMacchina;
	}

	@Override
	public String getInfoMacchina() {
		return json;
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
