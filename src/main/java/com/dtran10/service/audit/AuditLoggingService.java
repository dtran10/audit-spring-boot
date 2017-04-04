package com.dtran10.service.audit;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.dtran10.service.audit.aspect.Auditable;

import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
public class AuditLoggingService {

	@Auditable(eventType="Message Logging")
	public String log(String message) {
		String loggingMessage = "logging message: " + message;
		
		log.info(loggingMessage);
		
		return loggingMessage;
	}
	
	@Async
	@Auditable(eventType="Message Logging")
	public Future<String> asyncLog(String message) throws InterruptedException {
		String loggingMessage = "asynchronously logging message: " + message;
		
		Thread.sleep(5000);
		
		log.info(loggingMessage);
		
		return new AsyncResult<String>(loggingMessage);
	}
	
}
