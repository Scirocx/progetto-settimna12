package it.gestione.libreria.be.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.gestione.libreria.be.exception.AutoreException;
import it.gestione.libreria.be.model.Autore;
import it.gestione.libreria.be.repository.AutoreRepository;

@Service
public class AutoreService {

	@Autowired
	AutoreRepository auRepo;

	public List<Autore> findAll() {
		return auRepo.findAll();
	}

	public Optional<Autore> findAutoreById(Long id) {
		return auRepo.findById(id);

	}

	public Autore addAutore(Autore autore) {
		return auRepo.save(autore);
	}

	public Autore updateAutore(Autore autore, Long id) {
		Optional<Autore> autoreResult = auRepo.findById(id);
		if (autoreResult.isPresent()) {
			Autore autoreupdate = autoreResult.get();
			autoreupdate.setId(id);
			autoreupdate.setCognome(autore.getCognome());
			autoreupdate.setNome(autore.getNome());
			return auRepo.save(autoreupdate);
		} else {
			throw new AutoreException("autore non aggiornato");
		}
	}
	
	public void delete(Long id) {
		auRepo.deleteById(id);
	}

}
