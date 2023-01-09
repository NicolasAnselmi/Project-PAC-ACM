package com.datamanager;

import java.util.ArrayList;
import java.util.List;

import com.models.macchine.Macchina;

public class MacchineDataManager {

	/* SINGLETON PATTERN */
	private static MacchineDataManager m = null;

	// Array che rappresenta il DB delle macchine
	private ArrayList<Macchina> listaMacchine = new ArrayList<Macchina>();

	private MacchineDataManager() {

	}

	public static MacchineDataManager getMacchineDataManager() {
		if (m == null)
			m = new MacchineDataManager();
		return m;
	}
	
	/* PUSH */
	public void addMacchina(Macchina macchina) {
		listaMacchine.add(macchina);
	}
	
	/* PUT */
	public Macchina getMacchina(String idMacchina) {
		return listaMacchine.stream().filter(x -> x.getCodiceMacchina().equals(idMacchina)).findFirst().get();
	}
	
	public List<Macchina> getAllMacchine(){
		return listaMacchine;
	}
	
	/* DELETE */
	public boolean deleteMacchina(String idMacchina) {
		return listaMacchine.removeIf(x-> x.getCodiceMacchina().equals(idMacchina));
	}
	
	/* UPDATE */
	//TODO quali update?

}
