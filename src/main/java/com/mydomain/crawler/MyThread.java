package com.mydomain.crawler;

import java.util.concurrent.ThreadFactory;

public class MyThread implements ThreadFactory {
	private static int threadCount = 1;
	private static final Object mutex = new Object();

	public Thread newThread(Runnable r) {
		String threadName;
		synchronized (mutex) {
			threadName = "Worker-" + threadCount++;
		}
		Thread nt = new Thread(r);
		nt.setName(threadName);
		return nt;
	}
}
