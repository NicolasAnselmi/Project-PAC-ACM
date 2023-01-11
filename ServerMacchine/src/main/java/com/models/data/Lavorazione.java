package com.models.data;

import com.models.macchine.TipoMacchina;

public class Lavorazione {
	
	private String idLavorazione;
	private String idMacchina;
	private int slot;
	private Lotto lotto;
	private TipoMacchina tipoMacchina;
	
	public Lavorazione(String idLavorazione, Lotto lotto) {
		this.idLavorazione = idLavorazione;
		this.lotto = lotto;
	}
	
	public Lavorazione(String idLavorazione, Lotto lotto,String idMacchina, String timeStampInizio, String timeStampFine) {
		this(idLavorazione,lotto);
		this.idMacchina = idMacchina;
	}

	public String getIdLavorazione() {
		return idLavorazione;
	}

	public String getIdMacchina() {
		return idMacchina;
	}

	public Lotto getLotto() {
		return lotto;
	}

	public TipoMacchina getTipoMacchina() {
		return tipoMacchina;
	}
	
	
	

}
