package com.dtran10.service.audit.config;

import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dtran10.service.audit.repository.PersistentAuditEventRepositoryAdapter;

@Configuration
public class AuditConfig {

	@Bean
	public AuditEventRepository auditEventRepository() { 
		return new PersistentAuditEventRepositoryAdapter();
	}
}
