package com.mydomain.crawler;

import com.mydomain.crawler.service.CrawlSvc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class MainApp {
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(MainApp.class, args);

		if(args.length != 1) {
			System.err.println("Must supply exactly 1 argument");
			System.exit(1);
		}

		CrawlSvc svc = ctx.getBean(CrawlSvc.class);
		svc.workflow(args[0]);
	}
}
