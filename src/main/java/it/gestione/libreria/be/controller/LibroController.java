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
import it.gestione.libreria.be.model.Libro;
import it.gestione.libreria.be.service.LibroService;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api")
public class LibroController {

	@Autowired
	LibroService libroser;

	@GetMapping(path = "/libri")
	@Operation(summary = "mostra tutti i libri")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<List<Libro>> findAll() {
		List<Libro> findAll = libroser.findAllLibro();
		if (!findAll.isEmpty()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/libro/{id}")
	@Operation(summary = "mostra un libro passando un id")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Libro> findById(@PathVariable(required = true) Long id) {
		Optional<Libro> find = libroser.findLibroById(id);

		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(path = "/libro")
	@Operation(summary = "aggiunge un libro alla lista ", description = "per far funzionare il metodo "
			+ "all'id di categoria e autore deve essere passoato un id gia esistente nel database, "
			+ "altrimenti va in errore")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<Libro> save(@RequestBody Libro libro) {
		Libro save = libroser.addLibro(libro);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PutMapping(path = "/libro/{id}")
	@Operation(summary = "aggiorna un libro passandogli l'id del libro da aggiornare", 
	description = "per far funzionare il metodo all'id di categoria e "
			+ "autore deve essere passoato un id gia esistente nel database, "
			+ "altrimenti va in errore")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Libro> update(@PathVariable Long id, @RequestBody Libro libro) {
		Libro save = libroser.updateLibro(libro, id);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@DeleteMapping(path = "/libro/{id}")
	@Operation(summary = "cancella un libro passando l'id del libro")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		libroser.delete(id);
		return new ResponseEntity<>("libro deleted", HttpStatus.OK);

	}

}
