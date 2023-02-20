package lotto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import macchina.TipoMacchina;

public class Lavorazione implements Comparable<Lavorazione> {
	
	private String idLavorazione;
	private String idMacchina;
	private int slot;
	private String idLotto;
	private TipoMacchina tipoMacchina;
	
	@JsonCreator
	public Lavorazione(@JsonProperty("idLavorazione")String idLavorazione, @JsonProperty("lotto")String idLotto, @JsonProperty("tipoMacchina")String tipoMacchina,@JsonProperty("idMacchina")String idMacchina, @JsonProperty("slot")String slot) {
		this(idLavorazione, idLotto, tipoMacchina, idMacchina, Integer.parseInt(slot));
	}
	
	public Lavorazione(String idLavorazione, String idLotto, TipoMacchina tipoMacchina) {
		this.idLavorazione = idLavorazione;
		this.idLotto = idLotto;
		this.tipoMacchina = tipoMacchina;
	}
	
	public Lavorazione(String idLavorazione, String idLotto, TipoMacchina tipoMacchina,String idMacchina, int slot) {
		this(idLavorazione,idLotto, tipoMacchina);
		this.idMacchina = idMacchina;
		this.slot = slot;
	}
	public Lavorazione(String idLavorazione, String idLotto, String tipoMacchina,String idMacchina, int slot) {
		this(idLavorazione,idLotto, TipoMacchina.valueOf(tipoMacchina));
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

	public String getIdLotto() {
		return idLotto;
	}

	public TipoMacchina getTipoMacchina() {
		return tipoMacchina;
	}

	public int compareTo(Lavorazione o) {
		return this.slot - o.slot;
	}
	
	@Override
	public String toString() {
		return "lotto " + idLotto + ", slot: " + slot;
	}
	
	
	

}
