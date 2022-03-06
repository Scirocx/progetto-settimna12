package it.gestione.libreria.be.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.gestione.libreria.be.exception.CategoriaException;
import it.gestione.libreria.be.model.Categoria;
import it.gestione.libreria.be.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository cateRepo;
	
	public List<Categoria> findAllCategoria(){
		return cateRepo.findAll();
	}
	
	public Optional<Categoria> findCategoriaById(Long id){
		 return cateRepo.findById(id);
	}
	
	public Categoria addCategoria(Categoria categoria) {
		return cateRepo.save(categoria);
	}
	
	public Categoria updateCategoria(Categoria categoria, Long id) {
		Optional<Categoria> categoriaResult = cateRepo.findById(id);
		if(categoriaResult.isPresent()) {
			Categoria cateUpdate = categoriaResult.get();
			cateUpdate.setId(id);
			cateUpdate.setNome(categoria.getNome());
			return cateRepo.save(cateUpdate);
			 
			
		}else {
			throw new CategoriaException("categoria non aggiornata");
		}
	}
	
	public void delete(Long id) {
		cateRepo.deleteById(id);;
	}

}
