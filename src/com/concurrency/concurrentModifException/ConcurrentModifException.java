package com.concurrency.concurrentModifException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConcurrentModifException {
	static List<String> list;
	static Runnable runnable = ()->{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {}
		System.out.println("Child thread updating list.");
		list.add("EE");
	};

	public static void main(String[] args) {
		list = new ArrayList<>();
		list.add("AA");
		list.add("BB");
		list.add("CC");
		list.add("DD");
		
		Thread thread = new Thread(runnable);
		thread.start();
		Iterator<String> itr = list.iterator();
		while(itr.hasNext()){
			String string = itr.next();
			System.out.println("Main thread iterating list and current object is "+string);
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {}
		}

	}

}
