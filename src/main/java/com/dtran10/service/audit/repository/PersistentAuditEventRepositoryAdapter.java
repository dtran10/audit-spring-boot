package com.dtran10.service.audit.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.AuditEventRepository;

import com.dtran10.service.audit.model.PersistentAuditEvent;
import com.dtran10.service.audit.model.PersistentAuditEventData;

public class PersistentAuditEventRepositoryAdapter implements AuditEventRepository {
	
	@Autowired
	private PersistentAuditEventRepository repository;
	
	@Override
	public void add(@NotNull AuditEvent event) {
		repository.save(convert(event));
	}

	@Override
	public List<AuditEvent> find(@NotNull Date after) {
		return convert(repository.find(after));
	}

	@Override
	public List<AuditEvent> find(String principal, Date after) {
		return convert(repository.find(principal, after));
	}

	@Override
	public List<AuditEvent> find(String principal, Date after,
			String type) {
		return convert(repository.find(principal, after, type));
	}
	
	private PersistentAuditEvent convert(final AuditEvent event) {
		final PersistentAuditEvent pEvent = new PersistentAuditEvent();
		pEvent.setPrincipal(event.getPrincipal());
		pEvent.setType(event.getType());
		pEvent.setTime(event.getTimestamp());
		
		final List<PersistentAuditEventData> eventData = new ArrayList<>();
		for (Entry<String, Object> entry : event.getData().entrySet()) {
			final PersistentAuditEventData data = new PersistentAuditEventData();
			data.setDataKey(entry.getKey());
			if (entry.getValue() != null) {
				data.setDataValue(entry.getValue().toString());
			}
			eventData.add(data);
		}
		return pEvent;
	}
	
	private List<AuditEvent> convert(final List<PersistentAuditEvent> events) {
		List<AuditEvent> aEvents = new ArrayList<>();
		for (PersistentAuditEvent event : events) {
			aEvents.add(convert(event));
		}
		return aEvents;
	}
	
	private AuditEvent convert(final PersistentAuditEvent event) {
		Map<String, Object> data = new HashMap<>();
		for (PersistentAuditEventData eventData : event.getData()) {
			data.put(eventData.getDataKey(), eventData.getDataValue());
		}
		
		return new AuditEvent(event.getTime(), event.getPrincipal(), 
				event.getType(), data);
	}

}
