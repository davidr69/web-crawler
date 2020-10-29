package com.mydomain.crawler.service;

import com.mydomain.crawler.CrawlerException;
import com.mydomain.crawler.MyThread;
import com.mydomain.crawler.model.RadixTree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class CrawlSvc {
	private static final Logger logger = LoggerFactory.getLogger(CrawlSvc.class);
	private final ParseSvc parseSvc;
	private Integer activeThreads = 0;
	private ExecutorService executorService;
	private RadixTree tree;
	private String domain;
	// concurrency stuff
	private final Object mutex = new Object();
	private Lock lock = new ReentrantLock();

	@Value("${worker.threads}")
	private Integer THREADS;

	// constructor injection is preferred over field injection
	@Autowired
	public CrawlSvc(ParseSvc parseSvc) {
		this.parseSvc = parseSvc;
	}

	public void workflow(String domain) {
		logger.info("Workflow started");
		executorService = Executors.newFixedThreadPool(THREADS, new MyThread());
		this.domain = domain;

		List<String> anchors = null;
		try {
			anchors = parseSvc.parseUriToDocument(domain);
		} catch(CrawlerException e) {
			logger.error("Parser error: {}", e.getMessage());
			//return;
		}

		tree = new RadixTree(domain);
		// farm out threads
		work(anchors);
	}

	private void work(List<String> urls) {
		logger.info("work ... threads: {}", activeThreads);
		// get home page
		for(String uri: urls) {
			try {
				// multiple threads might try to update the tree, so ...
				boolean success = false;
				synchronized (mutex) {
					success = tree.put(uri);
				}
				if(success && uri.startsWith(domain)) {
					logger.info("process {}", uri);
					lock.lock();
					if(activeThreads == THREADS) {
						lock.unlock();
						while(true) {
							try {
								logger.info("sleeping ...");
								Thread.sleep(2000);
							} catch(InterruptedException e) {
								// noop
							}
							lock.lock();
							if(activeThreads < THREADS) {
								activeThreads++; // going to reserve a spot
								lock.unlock();
								break;
							}
							lock.unlock();
						}
					} else {
						activeThreads++;
						lock.unlock();
					}
					if(activeThreads < THREADS) {
						Callable<Integer> task = () -> {
							try {
								work(parseSvc.parseUriToDocument(uri));
								lock.lock();
								activeThreads--;
								lock.unlock();
							} catch(CrawlerException e) {
								logger.error("Thread error: {}", e.getMessage());
								return 1;
							}
							return 0;
						};
						executorService.submit(task);
					} else {
						logger.error("Too many threads");
					}
				}
			} catch(CrawlerException e) {
				logger.error("Radix tree error: {}", e.getMessage());
			}
		}
		//tree.display();
	}
}
