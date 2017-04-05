package com.dtran10.service.audit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dtran10.service.model.AbstractModelObject;

import lombok.Data;

@Table(name="audit_event_data")
@Data
@Entity
public class PersistentAuditEventData extends AbstractModelObject {

	private static final long serialVersionUID = -3173186395181502774L;

	@Id
	@GeneratedValue
	private String id;
	
	@Column(name="data_key", nullable=false)
	private String dataKey;
	
	@Column(name="data_value", nullable=false)
	@Lob
	private String dataValue;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="event_id")
	private PersistentAuditEvent event;
}
