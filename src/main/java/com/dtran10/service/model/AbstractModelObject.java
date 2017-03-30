package com.dtran10.service.model;

import java.io.Serializable;

import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Validated
@Data
public class AbstractModelObject implements Serializable {

	private static final long serialVersionUID = 5484411687909946425L;

}
