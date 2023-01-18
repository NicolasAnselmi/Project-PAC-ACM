package com.models.data;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.models.macchine.TipoMacchina;

public class Lavorazione implements Comparable<Lavorazione> {
	
	private String idLavorazione;
	private String idMacchina;
	private int slot;
	private Lotto lotto;
	private TipoMacchina tipoMacchina;
	
	@JsonCreator
	public Lavorazione(@JsonProperty("idLavorazione")String idLavorazione, @JsonProperty("lotto")Lotto lotto, @JsonProperty("tipoMacchina")String tipoMacchina,@JsonProperty("idMacchina")String idMacchina, @JsonProperty("slot")String slot) {
		this(idLavorazione, lotto, tipoMacchina, idMacchina, Integer.parseInt(slot));
	}
	
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
	public Lavorazione(String idLavorazione, Lotto lotto, String tipoMacchina,String idMacchina, int slot) {
		this(idLavorazione,lotto, TipoMacchina.valueOf(tipoMacchina));
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

	@Override
	public int compareTo(Lavorazione o) {
		return this.slot - o.slot;
	}
	
	
	

}
