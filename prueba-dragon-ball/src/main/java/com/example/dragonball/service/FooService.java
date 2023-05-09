package com.example.dragonball.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

@Service
public class FooService {

	@Autowired
	private Tracer tracer;
	
	private static final Logger log = LoggerFactory.getLogger(FooService.class);

	public void printLog() {
		
		Span newSpan = tracer.nextSpan().name("NewSpan");
		
		try ( Tracer.SpanInScope ws = this.tracer.withSpan(newSpan.start())){
			log.info("Test log");
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		} finally {
			newSpan.end();
		}
		
		Span newSpan2 = tracer.nextSpan().name("NewSpan2");
		try ( Tracer.SpanInScope ws = this.tracer.withSpan(newSpan2.start())){
			log.info("Test log");
		} finally {
			newSpan2.end();
		}
				
		
			log.info("Test log");
	}

}
