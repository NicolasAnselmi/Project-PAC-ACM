package com.models.data;

import java.util.List;

import com.models.macchine.TipoMacchina;

public class Lotto implements Comparable{

	private String idLotto;
	private String idProdotto;
	private int nPezzi;
	private PrioritaLotto priorita;
	private List<TipoMacchina> listaLavorazioni;

	/*public Lotto(String idLotto, String idProdotto, int nPezzi, String priorita, String[] listaLavorazioni) {
		this.idLotto = idLotto;
		this.idProdotto = idProdotto;
		this.nPezzi = nPezzi;
		this.priorita = PrioritaLotto.valueOf(priorita);
		this.listaLavorazioni = listaLavorazioni;
	}*/
	
	public Lotto(String idLotto, String idProdotto, int nPezzi, PrioritaLotto priorita, List<TipoMacchina> listaLavorazioni) {
		this.idLotto = idLotto;
		this.idProdotto = idProdotto;
		this.nPezzi = nPezzi;
		this.priorita = priorita;
		this.listaLavorazioni = listaLavorazioni;
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

	public void setnPezzi(int nPezzi) {
		this.nPezzi = nPezzi;
	}


	public void setPriorita(PrioritaLotto priorita) {
		this.priorita = priorita;
	}
	
	public List<TipoMacchina> getListaLavorazioni() {
		return listaLavorazioni;
	}


	@Override
	public int compareTo(Object o) {
		if(!(o instanceof Lotto))
			return -2000000;
		
		Lotto that = (Lotto)o;
		return priorita.ordinal()*1000 - listaLavorazioni.size() - (that.getPriorita().ordinal()*1000 - that.getListaLavorazioni().size());
	}
	

}
