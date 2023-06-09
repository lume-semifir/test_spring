package com.example.cuisine.categories;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("categories")
public class CategoriesController {

	private CategorieService categorieService;

	private CategoriesController(CategorieService categorieService) {
		this.categorieService = categorieService;
	}

	/**
	 * Récupérer la liste de toutes les catégorie
	 * 
	 * @return List<Categorie>
	 */
	@GetMapping
	public List<Categories> findAll() {
		return categorieService.findAll();
	}

	@GetMapping("{id}")
	public Categories findById(@PathVariable int id) {
		return categorieService.findById(id);
	}

	@GetMapping("nom")
	public Categories findByNom(@RequestParam String nom) {
		return categorieService.findByNom(nom);
	}

	@PostMapping
	public Categories save(@RequestBody Categories categories) {
		return categorieService.save(categories);
	}

	@DeleteMapping("{id}")
	public void deleteById(@PathVariable int id) {
		categorieService.deleteById(id);
	}

}
