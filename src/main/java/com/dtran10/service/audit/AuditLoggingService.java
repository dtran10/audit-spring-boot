package com.dtran10.service.audit;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.dtran10.service.audit.enums.AuditEventTypeEnum;

import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
public class AuditLoggingService {

	@Autowired
	private AuditEventRepository auditRepository;
	
	public String log(String message) {
		String loggingMessage = "logging message: " + message;
		
		log.info(loggingMessage);
		
		AuditEvent event = createAuditEvent(AuditEventTypeEnum.LOGGING, message);
		auditRepository.add(event);
		
		return loggingMessage;
	}
	
	@Async
	public Future<String> asyncLog(String message) throws InterruptedException {
		String loggingMessage = "asynchronously logging message: " + message;
		
		log.info(loggingMessage);
		
		AuditEvent event = createAuditEvent(AuditEventTypeEnum.LOGGING, message);
		auditRepository.add(event);
		
		return new AsyncResult<String>(loggingMessage);
	}
	
	private AuditEvent createAuditEvent(AuditEventTypeEnum auditEventType, Object data) {
		final String principal = "user";
		final Map<String, Object> eventData = new HashMap<>();
		
		eventData.put("key", data);
		
		final AuditEvent event = new AuditEvent(principal, auditEventType.getValue(), eventData);
		
		return event;
	}
	
}
