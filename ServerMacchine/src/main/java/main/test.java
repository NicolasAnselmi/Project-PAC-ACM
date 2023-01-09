package main;

import java.util.ArrayList;
import java.util.Collections;

import com.google.cloud.Timestamp;
import com.models.data.LogMacchina;

public class test {
	
	public static void main(String[] args) {
		System.out.println("Actual time: " + Timestamp.now().toDate());
		Timestamp t1 = Timestamp.now();
		LogMacchina l1 = new LogMacchina(0, "tornio1", "FINITO MATERIALI", "finiti materiali", t1.toString());
		System.out.println(l1);
		Timestamp t2 = Timestamp.now();
		LogMacchina l2 = new LogMacchina(1, "tornio2", "FINITO MATERIALI", "finiti materiali", t2.toString());
		System.out.println(l2);
		
		ArrayList<LogMacchina> list = new ArrayList<LogMacchina>();
		list.add(l2);
		list.add(l1);
		System.out.println(list.toString());
		Collections.sort(list);
		System.out.println(list.toString());
	}

}
