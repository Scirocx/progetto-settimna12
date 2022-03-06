package it.gestione.libreria.be.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.gestione.libreria.be.model.Autore;
import it.gestione.libreria.be.model.Categoria;
import it.gestione.libreria.be.model.Libro;
import it.gestione.libreria.be.repository.AutoreRepository;
import it.gestione.libreria.be.repository.CategoriaRepository;
import it.gestione.libreria.be.repository.LibroRepository;

@Component
public class ApplicationStartUpRunner implements CommandLineRunner {

	

	@Autowired
	LibroRepository libroRepo;
	
	@Autowired
	CategoriaRepository cateRepo;
	
	@Autowired
	AutoreRepository autRepo;

	@Override
	public void run(String... args) throws Exception {
		
		Autore autore = new Autore();
        autore.setNome("Christopher");
        autore.setCognome("Paolini");
        

        Autore autore1 = new Autore();
        autore1.setNome("Suzanne");
        autore1.setCognome("Collinse");
        autRepo.save(autore1);
        autRepo.save(autore);
        autRepo.flush();

        Categoria categoria1 = new Categoria();
        categoria1.setNome("Distopico");
        

        Categoria categoria = new Categoria();
        categoria.setNome("Fantasy");
        cateRepo.save(categoria);
        cateRepo.save(categoria1);
        cateRepo.flush();

        Libro libro = new Libro();
        libro.setTitolo("Eragon");
        libro.setAnnoPublicazione(2004);
        libro.setPrezzo(10.0);
        libro.getAutori().add(autore1);
        libro.getCategorie().add(categoria);
        libroRepo.save(libro);

        Libro libro1 = new Libro();
        libro1.setTitolo("Eldest");
        libro1.setAnnoPublicazione(2008);
        libro1.setPrezzo(12.0);
        libro1.getAutori().add(autore);
        libro1.getCategorie().add(categoria1);
        libroRepo.save(libro1);

        Libro libro2 = new Libro();
        libro2.setTitolo("Hunger Games: Il Canto della Rivolta");
        libro2.setAnnoPublicazione(2014);
        libro2.setPrezzo(15.0);
        libro2.getAutori().add(autore1);
        libro2.getCategorie().add(categoria1);
        libroRepo.save(libro2);
		

	}

}
