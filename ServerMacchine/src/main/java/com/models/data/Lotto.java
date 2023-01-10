package com.models.data;

public class Lotto {

	private String idLotto;
	private String idProdotto;
	private int nPezzi;
	private PrioritaLotto priorita;
	private float tempoLavorazionePezzoTornio;
	private float tempoLavorazionePezzoFresa;

	public Lotto(String idLotto, String idProdotto, int nPezzi, String priorita, float tempoLavorazionePezzoTornio, float tempoLavorazionePezzoFresa) {
		this.idLotto = idLotto;
		this.idProdotto = idProdotto;
		this.nPezzi = nPezzi;
		this.priorita = PrioritaLotto.valueOf(priorita);
		this.tempoLavorazionePezzoFresa = tempoLavorazionePezzoFresa;
		this.tempoLavorazionePezzoTornio = tempoLavorazionePezzoTornio;
	}

	
	public String getIdLotto() {
		return idLotto;
	}

	public String getIdProdotto() {
		return idProdotto;
	}

	public int getnPezzi() {
		return nPezzi;
	}

	public PrioritaLotto getPriorita() {
		return priorita;
	}

	public float getTempoLavorazionePezzoTornio() {
		return tempoLavorazionePezzoTornio;
	}

	public float getTempoLavorazionePezzoFresa() {
		return tempoLavorazionePezzoFresa;
	}


	public void setnPezzi(int nPezzi) {
		this.nPezzi = nPezzi;
	}


	public void setPriorita(PrioritaLotto priorita) {
		this.priorita = priorita;
	}


	public void setTempoLavorazionePezzoTornio(float tempoLavorazionePezzoTornio) {
		this.tempoLavorazionePezzoTornio = tempoLavorazionePezzoTornio;
	}


	public void setTempoLavorazionePezzoFresa(float tempoLavorazionePezzoFresa) {
		this.tempoLavorazionePezzoFresa = tempoLavorazionePezzoFresa;
	}
	
	
	
	

}
