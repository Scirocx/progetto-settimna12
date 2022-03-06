package it.gestione.libreria.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.gestione.libreria.be.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
