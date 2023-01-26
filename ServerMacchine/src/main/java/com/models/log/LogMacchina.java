package com.models.log;

import com.google.cloud.Timestamp;
import com.models.macchine.StatoMacchina;

public class LogMacchina implements Comparable<LogMacchina>{

	private String idLog;
	private String idLogger;
	private String title;
	private String body;
	private String timeStamp;
	private StatoMacchina statoMacchina;
	private String codiceLotto;
	
	
	public LogMacchina(String idLog, String idLogger, String title, String body, String statoMacchina, String codiceLotto) {
		this.idLog = idLog;
		this.idLogger = idLogger;
		this.title = title;
		this.body = body;
		this.timeStamp = Timestamp.now().toString();
		this.codiceLotto = codiceLotto;
		this.statoMacchina = StatoMacchina.valueOf(statoMacchina);
	}
	
	public StatoMacchina getStatoMacchina() {
		return statoMacchina;
	}
	
	public String getCodiceLotto() {
		return codiceLotto;
	}

	public String getIdLog() {
		return idLog;
	}

	public String getIdLogger() {
		return idLogger;
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	@Override
	public String toString() {
		return idLog + " " + timeStamp + ": " + idLogger + " -> " + title + "(" + body + ")\n";
	}

	@Override
	public int compareTo(LogMacchina o) {
		Timestamp o1 = Timestamp.parseTimestamp(this.timeStamp);
		Timestamp o2 = Timestamp.parseTimestamp(o.getTimeStamp());
		return o1.compareTo(o2);
	}

}
