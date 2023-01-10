package com.models.data;

public class Lavorazione {
	
	private String idLavorazione;
	private String idMacchina;
	private String timeStampInizio;
	private String timeStampFine;
	private Lotto lotto;
	
	public Lavorazione(String idLavorazione, Lotto lotto) {
		this.idLavorazione = idLavorazione;
		this.lotto = lotto;
	}
	
	public Lavorazione(String idLavorazione, Lotto lotto,String idMacchina, String timeStampInizio, String timeStampFine) {
		this(idLavorazione,lotto);
		this.idMacchina = idMacchina;
		this.timeStampInizio = timeStampInizio;
		this.timeStampFine = timeStampFine;
	}

	public String getIdLavorazione() {
		return idLavorazione;
	}

	public String getIdMacchina() {
		return idMacchina;
	}

	public String getTimeStampInizio() {
		return timeStampInizio;
	}

	public String getTimeStampFine() {
		return timeStampFine;
	}

	public Lotto getLotto() {
		return lotto;
	}
	
	

}
