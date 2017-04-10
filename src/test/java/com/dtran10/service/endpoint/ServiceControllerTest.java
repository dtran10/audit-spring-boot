package com.dtran10.service.endpoint;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.x509;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class ServiceControllerTest extends AbstractControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Value("${client.ssl.cert}")
	String clientCert;
	
	@Test
	@WithMockUser(roles = "USER")
	public void testSecure() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.get("/secure")
				.secure(true)
				.with(x509(clientCert))
				.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().string(equalTo("Access Granted!")));
		
	}
	
	@Test
	@WithMockUser(roles = "UNKNOWN_USER")
	public void testSecureDeniesUser() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.get("/secure")
				.secure(true)
				.with(x509(clientCert))
				.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isForbidden());
		
	}
	
}
