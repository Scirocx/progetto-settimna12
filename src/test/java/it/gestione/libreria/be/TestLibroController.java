package it.gestione.libreria.be;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TestLibroController {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test 
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN") 
	public void listalibriUtentiAutentuicati() throws Exception { 
		this.mockMvc.perform(get("/api/libri")).andExpect(status().isOk()); }

}
