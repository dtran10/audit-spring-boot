package com.dtran10.service.endpoint;

import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:/config/test.properties")
public abstract class AbstractControllerTest {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	/**
	 * Clears the security context after every test as to not leave a dirty context
	 * for another test.
	 */
	@After
	public void clearSecurity() {
		SecurityContextHolder.clearContext();
	}
	
}
