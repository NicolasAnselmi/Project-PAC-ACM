package com.datamanager;

import java.util.ArrayList;
import java.util.List;

import com.models.data.LogMacchina;

public class LogDataManager {

	/* SINGLETON PATTERN */
	private static LogDataManager m = null;

	// Array che rappresenta il DB dei Log
	private List<LogMacchina> listaLog = new ArrayList<LogMacchina>();

	private LogDataManager() {
		
	}

	public static LogDataManager getLogDataManager() {
		if (m == null)
			m = new LogDataManager();
		return m;
	}

	/* PUSH */
	public void addLogMacchina(LogMacchina log) {
		listaLog.add(log);
	}

	/* PUT */
	public List<LogMacchina> getLogByIdMacchina(String idMacchina) {
		return listaLog.stream().filter(x -> x.getIdLogger().equals(idMacchina)).toList();
	}

	public List<LogMacchina> getAllLogMacchina() {
		return listaLog;
	}

	
	/* DELETE */
	// TODO si eliminano? io non credo...

	/* UPDATE */
	// TODO non credo si facciano...

}
