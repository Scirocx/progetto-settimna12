package it.gestione.libreria.be.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.gestione.libreria.be.model.Autore;

import it.gestione.libreria.be.service.AutoreService;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api")
public class AutoreController {

	@Autowired
	AutoreService auService;

	@GetMapping(path = "/autori")
	@Operation(summary = "mostra tutti gli autori ")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<List<Autore>> findAll() {
		List<Autore> findAll = auService.findAll();
		if (!findAll.isEmpty()) {
			return new ResponseEntity<List<Autore>>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/autore/{id}")
	@Operation(summary = "mostra un autore passando l'id di quell'autore")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Autore> findById(@PathVariable(required = true) Long id) {
		Optional<Autore> find = auService.findAutoreById(id);

		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
	@PostMapping(path = "/autore")
	@Operation(summary = "salva un autore ")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Autore> save(@RequestBody Autore autore) {
		Autore save = auService.addAutore(autore);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}
	
	@PutMapping(path = "/autore/{id}")
	@Operation(summary = "aggiorna un libro passangoli un id del libro da aggiornare")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Autore> update(@PathVariable Long id, @RequestBody Autore autore) {
	Autore save = auService.updateAutore(autore, id);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}
	
	@DeleteMapping(path = "/autore/{id}")
	@Operation(summary = "elima un autore passando l'id dell'autore da eliminare")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		auService.delete(id);
		return new ResponseEntity<>("libro deleted", HttpStatus.OK);

	}


}
