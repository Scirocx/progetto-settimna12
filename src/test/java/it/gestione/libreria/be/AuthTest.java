package it.gestione.libreria.be;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import it.gestione.libreria.be.model.Role;
import it.gestione.libreria.be.model.Roles;
import it.gestione.libreria.be.model.User;
import it.gestione.libreria.be.repository.RoleRepository;
import it.gestione.libreria.be.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthTest {
	
	@Autowired
	private MockMvc mockmvc;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Test 
	@WithAnonymousUser 
	public void listaAutoriWhenUtenteIsAnonymous() throws Exception { 
		this.mockmvc.perform(get("/api/autore")).andExpect(status().isUnauthorized()); 
		}
	
	@Test 
	@WithAnonymousUser 
	public void testLoginPublica() throws Exception { 
		this.mockmvc.perform(get("/auth/login")).andExpect(status().isMethodNotAllowed()); }
	
//	@Test 
//	public void utenteRealeGetTokenAndAuthentication() throws Exception { 
//		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
//		Role role = new Role();
//		role.setRoleName(Roles.ADMIN);
//		User user = new User();
//		Set<Role> roles = new HashSet<>(); 
//		roles.add(role);
//		user.setUserName("admin");
//		user.setPassword(bCrypt.encode("admin"));
//		user.setEmail("admin@domain.com");
//		user.setRoles(roles);
//		user.setActive(true);
//		
//		roleRepo.save(role);
//		userRepo.save(user);
//		String username = "admin"; String password = "admin"; 
//		String body = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}"; 
//		MvcResult result = mockmvc.perform(MockMvcRequestBuilders.post("/auth/login") 
//				.contentType(MediaType.APPLICATION_JSON) .
//				content(body)) 
//				.andExpect(status().isOk())
//				.andReturn(); 
//		String response = result.getResponse().getContentAsString(); 
//		String token = response.split(",")[0]; 
//		token = token.substring(10, token.length() - 1); 
//		mockmvc.perform(MockMvcRequestBuilders.get("/api/autore") 
//				.header("Authorization", "Bearer " + token)) 
//		.andExpect(status().isOk()); }
//	
	


}
