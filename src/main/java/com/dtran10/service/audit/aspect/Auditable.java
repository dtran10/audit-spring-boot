package com.dtran10.service.audit.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for generating audit events when the annotated method is called. Can
 * be configured to specify the event type and other attributes about the event.
 * 
 * @author Jason Luck
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Auditable {
	
	/**
	 * The type of audit event. Synonym
	 * for {@link #value()}.
	 * @return
	 */
	String eventType();
}

