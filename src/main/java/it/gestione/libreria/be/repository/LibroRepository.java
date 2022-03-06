package it.gestione.libreria.be.repository;




import org.springframework.data.jpa.repository.JpaRepository;


import it.gestione.libreria.be.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{

	//getAllLibro e il findBy autore
}
