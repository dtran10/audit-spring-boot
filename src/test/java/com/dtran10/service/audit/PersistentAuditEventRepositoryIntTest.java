package com.dtran10.service.audit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dtran10.service.AbstractBaseIntTest;
import com.dtran10.service.audit.model.PersistentAuditEvent;
import com.dtran10.service.audit.model.PersistentAuditEventData;

public class PersistentAuditEventRepositoryIntTest extends AbstractBaseIntTest {
	
	@Autowired
	private PersistentAuditEventRepository repository;
	
	@Test
	public void save() {
		PersistentAuditEvent entity = createEntity();
		
		Assert.assertNull(entity.getId());
		PersistentAuditEvent result = repository.save(entity);
		Assert.assertNotNull(result.getId());
		Assert.assertNotNull(result.getData().get(0).getId());
		Assert.assertEquals(result.getId(), entity.getId());
		Assert.assertEquals(result, entity);
	}
	
	@Test
	public void saveList() {
		PersistentAuditEvent entity = createEntity();
		PersistentAuditEvent entity2 = createEntity();
		
		List<PersistentAuditEvent> entities = new ArrayList<>();
		entities.add(entity);
		entities.add(entity2);
		
		Iterable<PersistentAuditEvent> results = repository.save(entities);
		for (PersistentAuditEvent result : results) {
			Assert.assertTrue(result.equals(entity) || result.equals(entity2));
		}
	}
	
	protected PersistentAuditEvent createEntity() {
		final PersistentAuditEvent entity = new PersistentAuditEvent();
		entity.setPrincipal("user");
		entity.setType("test");
		
		List<PersistentAuditEventData> data = new ArrayList<>();
		final PersistentAuditEventData dataEntity = new PersistentAuditEventData();
		dataEntity.setDataKey("test1");
		dataEntity.setDataValue("test");
		dataEntity.setEvent(entity);
		data.add(dataEntity);
		
		entity.setData(data);
		return entity;
	}

}
