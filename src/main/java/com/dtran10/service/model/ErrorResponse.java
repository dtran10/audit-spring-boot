package com.dtran10.service.model;

import lombok.Getter;
import lombok.Setter;

public class ErrorResponse extends AbstractModelObject {

	private static final long serialVersionUID = -5473914744792790818L;
	
	@Getter
	@Setter
	private String code;
	
	@Getter
	@Setter
	private String messsage;

}
