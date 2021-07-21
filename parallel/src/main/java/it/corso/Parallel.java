package it.corso;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Parallel {
	
	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
		ExecutorService es1 = Executors.newFixedThreadPool(100);
		
		for (int i = 0; i < 1_000; i++) {
			es.execute(() -> {
				for(int j = 0; j < 3; j++) {
					String s = Thread.currentThread().getName() + LocalTime.now();
					System.out.println(s);
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		Instant i1 = Instant.now();
		
		for (int i = 0; i < 1_000; i++) {
			es1.execute(() -> {
				for(int j = 0; j < 3; j++) {
					String s = Thread.currentThread().getName() + LocalTime.now();
					System.out.println(s);
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		Instant i2 = Instant.now();
		Duration d1 = Duration.between(i1, i2);
		System.out.println("tempo: " + d1);
	}
	
}
