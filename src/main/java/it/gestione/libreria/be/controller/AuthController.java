package it.gestione.libreria.be.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.gestione.libreria.be.exception.RegistrazioneException;
import it.gestione.libreria.be.model.LoginRequest;
import it.gestione.libreria.be.model.LoginResponse;
import it.gestione.libreria.be.model.RequestRegisterUser;
import it.gestione.libreria.be.model.Role;
import it.gestione.libreria.be.model.Roles;
import it.gestione.libreria.be.model.User;
import it.gestione.libreria.be.repository.RoleRepository;
import it.gestione.libreria.be.repository.UserRepository;
import it.gestione.libreria.be.service.UserDetailsImpl;
import it.gestione.libreria.be.util.JwtUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	RoleRepository roleRepo;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		LoginResponse loginResponse = new LoginResponse();

		loginResponse.setToken(token);
		loginResponse.setRoles(roles);

		return ResponseEntity.ok(loginResponse);
	}

	@PostMapping(path = "/signup")
	public ResponseEntity<?> userRegister(@RequestBody RequestRegisterUser requestUser) {
		if (userRepository.existsByEmail(requestUser.getEmail())) {
			return new ResponseEntity<String>("email gia associata ad un account", HttpStatus.BAD_REQUEST);
		} else if (userRepository.existsByUserName(requestUser.getUserName())) {
			return new ResponseEntity<String>("account già esistente", HttpStatus.BAD_REQUEST);
		}
		User userRegistrato = new User();
		userRegistrato.setUserName(requestUser.getUserName());
		userRegistrato.setPassword(encoder.encode(requestUser.getPassword()));
		userRegistrato.setEmail(requestUser.getEmail());
		if(requestUser.getRoles().isEmpty()){
			Optional<Role> ruolo = roleRepo.findByRoleName(Roles.ROLE_USER);
			Set<Role> ruoli = new HashSet<Role>();
			ruoli.add(ruolo.get());
			userRegistrato.setRoles(ruoli);
		}else {
			Set<Role> ruoli = new HashSet<Role>();
			for(String set : requestUser.getRoles()) {
				switch(set.toUpperCase()) {
				case"ADMIN":
					Optional<Role> ruolo1= roleRepo.findByRoleName(Roles.ROLE_ADMIN);
					ruoli.add(ruolo1.get());
					break;
				case"USER":
					Optional<Role> ruolo2= roleRepo.findByRoleName(Roles.ROLE_USER);
					ruoli.add(ruolo2.get());
					break;
					default:
						throw new RegistrazioneException("ruolo non trovato"); 
				}
			}
			userRegistrato.setRoles(ruoli);
			
		}
		userRepository.save(userRegistrato);
		return new ResponseEntity<>("utente registrato con successo :" + userRegistrato.toString(),HttpStatus.CREATED);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}