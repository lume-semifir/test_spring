package com.example.cuisine.ingredients;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("ingredients")
public class IngredientsController {

	private IngredientsService ingredientsService;

	// Constructor
	public IngredientsController(IngredientsService ingredientsService) {
		this.ingredientsService = ingredientsService;
	}

	/**
	 * Récupère la liste de tous les ingrédients
	 * 
	 * @return List<Ingrediant>
	 */
	@GetMapping("")
	public List<Ingredient> index() {
		return ingredientsService.findAll();
	}

	/**
	 * Créer un nouvel ingrédient
	 * 
	 * @param ingrediant Ingredient
	 * @return Ingredient
	 */
	@PostMapping("")
	public Ingredient post(@RequestBody Ingredient ingredient) {
		return ingredientsService.save(ingredient);
	}

	/**
	 * Récupère un ingrédient par son id
	 * 
	 * @param id int
	 * @return Ingredient
	 */
	@GetMapping("/{id}")
	public Ingredient getById(@PathVariable Integer id) {
		return ingredientsService.findById(id);
	}

	/**
	 * Supprime un ingrédient par son id
	 * 
	 * @param id int
	 */
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Integer id) {
		ingredientsService.deleteById(id);
	}

	/**
	 * Met à jour un ingrédient
	 * 
	 * @param id         int
	 * @param ingrediant Ingredient
	 */
	@PutMapping("/{id}")
	public void update(@PathVariable int id, @RequestBody Ingredient ingredient) {
		ingredient.setId(id);
		ingredientsService.update(ingredient);
	}
}
