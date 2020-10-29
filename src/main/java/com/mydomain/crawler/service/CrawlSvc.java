package com.mydomain.crawler.service;

import com.mydomain.crawler.CrawlerException;
import com.mydomain.crawler.model.RadixTree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CrawlSvc {
	private static final Logger logger = LoggerFactory.getLogger(CrawlSvc.class);
	private final ParseSvc parseSvc;
	private RadixTree tree;
	private String domain;

	@Value("${worker.threads}")
	private Integer THREADS;

	// constructor injection is preferred over field injection
	@Autowired
	public CrawlSvc(ParseSvc parseSvc) {
		this.parseSvc = parseSvc;
	}

	public void workflow(String domain) {
		logger.info("Workflow started");
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
		tree.display();
	}

	private void work(List<String> urls) {
		logger.info("work ...");
		// get home page
		for(String uri: urls) {
			try {
				// multiple threads might try to update the tree, so ...
				if(tree.put(uri) && uri.startsWith(domain)) {
					logger.info("process {}", uri);
					work(parseSvc.parseUriToDocument(uri));
				}
			} catch(CrawlerException e) {
				logger.error("Radix tree error: {}", e.getMessage());
			}
		}
		//tree.display();
	}
}
