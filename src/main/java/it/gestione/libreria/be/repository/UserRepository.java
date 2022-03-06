package it.gestione.libreria.be.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.gestione.libreria.be.model.User;



public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findById(Integer id);
	public Optional<User> findByUserName(String userName);
	public boolean existsByEmail(String email);
	public boolean existsByUserName(String userName);
}
