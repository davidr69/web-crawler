package com.mydomain.crawler;

public class CrawlerException extends Throwable {
	public CrawlerException(String msg) {
		super(msg);
	}

	public CrawlerException(String msg, Throwable e) {
		super(msg, e);
	}
}
