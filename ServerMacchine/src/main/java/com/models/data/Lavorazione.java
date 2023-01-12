package com.models.data;

import com.models.macchine.TipoMacchina;

public class Lavorazione {
	
	private String idLavorazione;
	private String idMacchina;
	private int slot;
	private Lotto lotto;
	private TipoMacchina tipoMacchina;
	
	public Lavorazione(String idLavorazione, Lotto lotto, TipoMacchina tipoMacchina) {
		this.idLavorazione = idLavorazione;
		this.lotto = lotto;
		this.tipoMacchina = tipoMacchina;
	}
	
	public Lavorazione(String idLavorazione, Lotto lotto, TipoMacchina tipoMacchina,String idMacchina, int slot) {
		this(idLavorazione,lotto, tipoMacchina);
		this.idMacchina = idMacchina;
		this.slot = slot;
	}
	
	public int getSlot() {
		return slot;
	}

	public String getIdLavorazione() {
		return idLavorazione;
	}

	public String getIdMacchina() {
		return idMacchina;
	}

	public String getLotto() {
		return lotto.getIdLotto();
	}

	public TipoMacchina getTipoMacchina() {
		return tipoMacchina;
	}
	
	
	

}
