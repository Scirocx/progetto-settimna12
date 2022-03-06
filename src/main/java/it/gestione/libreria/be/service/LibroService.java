package it.gestione.libreria.be.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import it.gestione.libreria.be.exception.LibroExcemption;
import it.gestione.libreria.be.model.Libro;
import it.gestione.libreria.be.repository.CategoriaRepository;
import it.gestione.libreria.be.repository.LibroRepository;

@Service
public class LibroService {

	@Autowired
	LibroRepository libroRepo;
	@Autowired
	CategoriaRepository categoriaRepo;

//	Page<Libro> findLibriByAutori(List<Long> id, Pageable pageable){
//		return libroRepo.findLibriByAutori(id, pageable);
//	}

	public List<Libro> findAllLibro() {
		return  libroRepo.findAll();
	}

	public Optional<Libro> findLibroById(Long id) {
		return libroRepo.findById(id);
	}
	

	public Libro addLibro(Libro libro) {
		 return libroRepo.save(libro);
	}

	public Libro updateLibro(Libro libro, Long id) {
		Optional<Libro> libroResult = libroRepo.findById(id);
		if (libroResult.isPresent()) {
			Libro libroupdate = libroResult.get();
			libroupdate.setId(id);
			libroupdate.setTitolo(libro.getTitolo());
			libroupdate.setAnnoPublicazione(libro.getAnnoPublicazione());
			libroupdate.setAutori(libro.getAutori());
			libroupdate.setPrezzo(libro.getPrezzo());
			libroupdate.setCategorie(libro.getCategorie());
			libroRepo.save(libroupdate);
			return libroupdate;
		}
		else {
			throw new LibroExcemption("libro non aggiornato");
		}
	}

	public void delete(Long id) {
		libroRepo.deleteById(id);
	}
}
