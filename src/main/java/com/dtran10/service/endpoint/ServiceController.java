package com.dtran10.service.endpoint;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dtran10.service.audit.AuditLoggingService;

@RestController
public class ServiceController {
	
	@Autowired
	AuditLoggingService auditLoggingService;
	
	@RequestMapping(method=RequestMethod.GET, value="/hello")
	public ResponseEntity<String> hello() {
		return new ResponseEntity<String>("Hello There!", HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/log")
	public ResponseEntity<String> log(@RequestBody String message) {
		String logMessage = auditLoggingService.log(message);
		return new ResponseEntity<String>(logMessage, HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST, value="/async-log")
	public ResponseEntity asyncLog(@RequestBody String message) throws InterruptedException {
		auditLoggingService.asyncLog(message);
		
		return new ResponseEntity(HttpStatus.CREATED);
	}
}
