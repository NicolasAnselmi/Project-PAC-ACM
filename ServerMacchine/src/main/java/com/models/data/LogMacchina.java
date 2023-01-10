package com.models.data;

import com.google.cloud.Timestamp;

public class LogMacchina implements Comparable<LogMacchina>{

	private long idLog;
	private String idLogger;
	private String title;
	private String body;
	private String timeStamp;
	
	public LogMacchina(long idLog, String idLogger, String title, String body) {
		this.idLog = idLog;
		this.idLogger = idLogger;
		this.title = title;
		this.body = body;
		this.timeStamp = Timestamp.now().toString();
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
