package it.gestione.libreria.be.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.gestione.libreria.be.model.User;
import it.gestione.libreria.be.repository.UserRepository;



@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
	}

}
