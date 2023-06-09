package com.example.demo.personne;

import java.util.*;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/personnes")
public class PersonneController {

	private PersonneService personneService;
	
	public PersonneController(PersonneService personneService) {
		this.personneService = personneService;
	}
	
	@GetMapping
	public List<Personne> findAll(){
		return personneService.findAll();
	}
	
	@PostMapping
	public void save(@RequestBody Personne personne) {
		personneService.save(personne);
	}
	
	@GetMapping("/{id}")
	public Personne findById(@PathVariable int id){
		return personneService.findById(id);
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable int id) {
		personneService.deleteById(id);
	}
	
	@PatchMapping("/{id}")
	public void update(@PathVariable int id, @RequestBody Personne personne) {
		personneService.findById(id);
		personneService.update(id, personne);
	}
}
