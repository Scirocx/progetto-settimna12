package it.gestione.libreria.be.model;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Libro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Column(nullable = false)
	private String titolo;

	@Column(nullable = false)
	private int annoPublicazione;
	@Column(nullable = false)
	private double prezzo;

	@ManyToMany
	@JoinTable(name = "libri_autori", joinColumns = @JoinColumn(name = "libro_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "autore_id", referencedColumnName = "id"))
	private Set<Autore> autori = new HashSet<Autore>();

	@ManyToMany
	@JoinTable(name = "libri_categorie", joinColumns = @JoinColumn(name = "libro_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "id"))
	private Set<Categoria> categorie = new HashSet<Categoria>();

}
