package com.datamanager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;

import com.models.macchine.Macchina;
import com.models.macchine.StatoMacchina;

public class MacchineDataManager {

	/* SINGLETON PATTERN */
	private static MacchineDataManager m = null;

	// Array che rappresenta il DB delle macchine
	private ArrayList<Macchina> listaMacchine = null;

	private MacchineDataManager() {
		listaMacchine = new ArrayList<Macchina>();
	}

	@Bean("macchineDataManager")
	public static MacchineDataManager getMacchineDataManager() {
		if (m == null)
			m = new MacchineDataManager();
		return m;
	}
	
	/* PUSH */
	public Macchina addMacchina(String idMacchina, String tipoMacchina) {
		Macchina m = new Macchina(idMacchina, tipoMacchina);
		listaMacchine.add(m);
		return m;
	}
	
	/* PUT */
	public String getInfoMacchina(String idMacchina) {
		return listaMacchine.stream().filter(x -> x.getidMacchina().equals(idMacchina)).findFirst().get().toString();
	}
	
	public List<Macchina> getAllMacchine(){
		return listaMacchine;
	}

	
	
	/* DELETE */
	public boolean deleteMacchina(String idMacchina) {
		return listaMacchine.removeIf(x-> x.getidMacchina().equals(idMacchina));
	}

	public List<Macchina> getMacchineByStato(String stato) {
		StatoMacchina s = StatoMacchina.valueOf(stato);
		return listaMacchine.stream().filter(x->x.getStatoMacchina().equals(s)).toList();
	}

	public List<Macchina> getMacchineById(String[] listaMacchine) {
		return this.listaMacchine.stream().filter(x-> equalsAnyOf(x.getidMacchina(), listaMacchine)).toList();
	}
	
	public void aggiornaMacchina(String IDMacchina, String codiceLotto, String timestamp, StatoMacchina statoMacchina)
	{
		for (Macchina macchina : listaMacchine) {
			if (macchina.getidMacchina().equals(IDMacchina)) {
				macchina.aggiornaMacchina(codiceLotto, timestamp, statoMacchina);
				break;
			}		
		}
	}
	
	private boolean equalsAnyOf(String string, String[] list) {
		for (String s : list) {
			if(s.equals(string))
				return true;
		}
		return false;
	}

}
