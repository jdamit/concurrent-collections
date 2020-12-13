package com.concurrency.concurrentMap;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapFacts {

	static ConcurrentHashMap<Integer, String> map;
	static Runnable runnable = ()->{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {}
		System.out.println("Child thread updating map.");
		map.put(103, "FF");
	};
	public static void main(String[] args) {
		map = new ConcurrentHashMap<>();
		
		map.put(101, "AA");
		map.put(102, "BB");
		map.put(103, "CC");
		map.put(104, "DD");
		System.out.println(map);
		System.out.println("--------------------------------");
		map.putIfAbsent(101, "EE");
		System.out.println(map);
		System.out.println("--------------------------------");
		map.remove(103,"EE");
		System.out.println(map);
		System.out.println("--------------------------------");
		map.replace(103, "CC", "EE");
		System.out.println(map);
		
		Thread thread = new Thread(runnable);
		thread.start();
		map.forEach((key, value)->{
			System.out.println("Main thread iterating map and current key: "+key+", value: "+value);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {}
		});
		
	}

}
