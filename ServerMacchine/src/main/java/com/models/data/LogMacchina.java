package com.models.data;

public class LogMacchina {

	private long idLog;
	private String idLogger;
	private String title;
	private String body;
	
	public LogMacchina(long idLog, String idLogger, String title, String body) {
		this.idLog = idLog;
		this.idLogger = idLogger;
		this.title = title;
		this.body = body;
	}

	public long getIdLog() {
		return idLog;
	}

	public String getIdLogger() {
		return idLogger;
	}

}
