package main;

import java.util.ArrayList;
import java.util.Collections;

import com.google.cloud.Timestamp;
import com.models.log.LogMacchina;

public class test {
	
	public static void main(String[] args) {
		System.out.println("Actual time: " + Timestamp.now().toDate());
		LogMacchina l1 = new LogMacchina("0", "tornio1", "FINITO MATERIALI", "finiti materiali","ferma","pippo");
		System.out.println(l1);
		LogMacchina l2 = new LogMacchina("1", "tornio2", "FINITO MATERIALI", "finiti materiali","ferma","pippo");
		System.out.println(l2);
		
		ArrayList<LogMacchina> list = new ArrayList<LogMacchina>();
		list.add(l2);
		list.add(l1);
		System.out.println(list.toString());
		Collections.sort(list);
		System.out.println(list.toString());
		
		Timestamp t1 = Timestamp.now();
		for (int i = 0; i < 10000000; i++) {
		}
		Timestamp t2 = Timestamp.now();
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t2.compareTo(t2));
	}

}
