package com.example.cuisine.recettes;

import java.util.List;

import com.example.cuisine.categories.Categories;
import com.example.cuisine.ingredientsQuantite.IngredientsQuantite;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Recettes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String description;
	
	@OneToMany
	private List<IngredientsQuantite> ingredientsQuantite;
	
	@ManyToMany
	@JoinTable(
			name = "recette_categorie",
	        joinColumns = @JoinColumn(name = "categories_id"),
	        inverseJoinColumns = @JoinColumn(name = "recettes_id")
			)
	private List<Categories> categories;
	
	
}
