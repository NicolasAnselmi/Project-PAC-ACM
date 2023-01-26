package com.models.log;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ServiceLog {
	
	private LogDataManager logDataManager = LogDataManager.getLogDataManager();
	
	public void addLogMacchina(LogMacchina log) {
		logDataManager.addLogMacchina(log);
	}
	
	public List<LogMacchina> getLogByIdMacchina(String idMacchina) {
		return logDataManager.getLogByIdMacchina(idMacchina);
	}
	
	public List<LogMacchina> getAllLogMacchina() {
		return logDataManager.getAllLogMacchina();
	}
	
}
