package com.dtran10.service.audit.enums;

public enum AuditEventTypeEnum {
	LOGGING("LOGGING"),
	UNCATEGORIZED("UNCATEGORIZED");
	
	private final String eventType;
	
	AuditEventTypeEnum(String eventType) {
		this.eventType = eventType;
	}
	
	public String getValue() {
		return eventType;
	}

	public static AuditEventTypeEnum parse(String eventType) {
		for (AuditEventTypeEnum e: AuditEventTypeEnum.values()) {
			if (e.getValue().equals(eventType)) {
				return e;
			}
		}
		
		return null;
	}
}
