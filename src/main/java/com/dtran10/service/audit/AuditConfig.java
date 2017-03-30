package com.dtran10.service.audit;

import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditConfig {

	@Bean
	public AuditEventRepository auditEventRepository() { 
		return new AuditEventRepositoryAdapter();
	}
}
