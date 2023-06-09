package com.example.cuisine.categories;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CategorieService {
	
	private CategoriesRepository categoriesRepository;
	
	public CategorieService(CategoriesRepository categoriesRepository) {
		this.categoriesRepository = categoriesRepository;
	}
	
	public List<Categories> findAll(){
		return categoriesRepository.findAll();
	}
	
	public Categories findById(int id) {
		return categoriesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categorie non trouvé"));
	}
	
	public Categories findByNom(String nom) {
		return categoriesRepository.findByNom(nom);
	}
	
	public Categories save(Categories categories) {
		categoriesRepository.save(categories);
		return categories;
	}
	
	public void deleteById(int id) {
		categoriesRepository.deleteById(id);
	}

}
