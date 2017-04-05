package com.dtran10.service.audit.aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.stereotype.Component;

import lombok.extern.apachecommons.CommonsLog;

@Component
@Aspect
@CommonsLog
public class AuditAdvice {
	
	@Autowired
	private AuditEventRepository auditRepository;

	@Before("@annotation(auditable)")
	public void fireAuditEvent(Auditable auditable) {
		final String principal = "user";
		final Map<String, Object> eventData = new HashMap<>();
		
		final AuditEvent event = new AuditEvent(principal, auditable.eventType(), eventData);
		
		log.info("Firing Audit Event: " + event);
		auditRepository.add(event);
	}

}
