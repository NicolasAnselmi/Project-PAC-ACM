package com.models.log;

import java.util.ArrayList;
import java.util.List;

public class LogDataManager {

	/* SINGLETON PATTERN */
	private static LogDataManager m = null;

	// Array che rappresenta il DB dei Log
	private List<LogMacchina> listaLog = null;

	private LogDataManager() {
		listaLog = new ArrayList<LogMacchina>();
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
