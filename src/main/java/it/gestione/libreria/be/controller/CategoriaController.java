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
import it.gestione.libreria.be.model.Categoria;

import it.gestione.libreria.be.service.CategoriaService;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api")
public class CategoriaController {
	
	@Autowired
	CategoriaService cateService;
	
	@GetMapping(path = "/categorie")
	@Operation(summary = "mostra tutte le cvategorie presenti nella lista")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<List<Categoria>> findAll(){
		List<Categoria> findAll= cateService.findAllCategoria();
		if(!findAll.isEmpty()) {
			return new ResponseEntity<List<Categoria>>(findAll, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(path = "/categoria/{id}")
	@Operation(summary = "mostra una categoria passando un id")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Categoria> findById(@PathVariable(required = true) Long id) {
		Optional<Categoria> find = cateService.findCategoriaById(id);

		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping(path = "/categoiria")
	@Operation(summary = "salva una nuova categoria nella lista")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Categoria> save(@RequestBody Categoria categoria) {
		Categoria save = cateService.addCategoria(categoria);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}
	
	@PutMapping(path = "/categoria/{id}")
	@Operation(summary = "aggiorna una categoria tramite passandogli l'id della categoria da aggiornare")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody Categoria categoria) {
	Categoria save = cateService.updateCategoria(categoria, id);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}
	
	
	@DeleteMapping(path = "/categoria/{id}")
	@Operation(summary = "cancella una categoria tramite l'id di quella categoria")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		cateService.delete(id);
		return new ResponseEntity<>("categoria deleted", HttpStatus.OK);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
