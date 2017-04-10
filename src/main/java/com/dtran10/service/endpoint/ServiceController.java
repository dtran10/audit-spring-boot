package com.dtran10.service.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dtran10.service.audit.AuditLoggingService;

@RestController
public class ServiceController {
	
	@Autowired
	AuditLoggingService auditLoggingService;
	
	@Secured("ROLE_USER")
	@RequestMapping(method=RequestMethod.GET, value="/secure")
	public ResponseEntity<String> hello() {
		return new ResponseEntity<String>("Access Granted!", HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/log")
	@ResponseBody
	public ResponseEntity<String> log(@RequestBody String message) {
		String logMessage = auditLoggingService.log(message);
		return new ResponseEntity<String>(logMessage, HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST, value="/async-log")
	@ResponseBody
	public ResponseEntity<String> asyncLog(@RequestBody String message) throws InterruptedException {
		auditLoggingService.asyncLog(message);
		
		return new ResponseEntity<String>("audit logging process kicked off", HttpStatus.CREATED);
	}
}
