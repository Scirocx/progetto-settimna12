package it.gestione.libreria.be.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.gestione.libreria.be.model.Role;
import it.gestione.libreria.be.model.Roles;



public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByRoleName(Roles role);
}
