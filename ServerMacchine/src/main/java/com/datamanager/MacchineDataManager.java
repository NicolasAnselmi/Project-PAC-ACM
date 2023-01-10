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
	private ArrayList<Macchina> listaMacchine = new ArrayList<Macchina>();

	private MacchineDataManager() {

	}

	@Bean("macchineDataManager")
	public static MacchineDataManager getMacchineDataManager() {
		if (m == null)
			m = new MacchineDataManager();
		return m;
	}
	
	/* PUSH */
	public void addMacchina(String idMacchina) {
		Macchina m = new Macchina(idMacchina);
		listaMacchine.add(m);
	}
	
	/* PUT */
	public String getInfoMacchina(String idMacchina) {
		return listaMacchine.stream().filter(x -> x.getidMacchina().equals(idMacchina)).findFirst().get().getInfoMacchina();
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

}
