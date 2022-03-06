package it.gestione.libreria.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.gestione.libreria.be.model.Autore;

public interface AutoreRepository  extends JpaRepository<Autore, Long>{

}
