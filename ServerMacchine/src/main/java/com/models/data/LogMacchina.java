package com.models.data;

import com.google.cloud.Timestamp;
import com.models.macchine.StatoMacchina;

public class LogMacchina implements Comparable<LogMacchina>{

	private long idLog;
	private String idLogger;
	private String title;
	private String body;
	private String timeStamp;
	private StatoMacchina statoMacchina;
	private int codiceLotto;
	
	
	public LogMacchina(long idLog, String idLogger, String title, String body, String statoMacchina, int codiceLotto) {
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
	
	public int getCodiceLotto() {
		return codiceLotto;
	}

	public long getIdLog() {
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
