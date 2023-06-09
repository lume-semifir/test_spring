package com.example.fr.omg.elkino.films;

import java.util.List;

import org.hibernate.PropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.fr.omg.elkino.acteurs.Acteur;
import com.example.fr.omg.elkino.acteurs.dto.ActeurSansFilmDto;
import com.example.fr.omg.elkino.exceptions.BadRequestExcepption;
import com.example.fr.omg.elkino.films.dto.FilmCompletDto;
import com.example.fr.omg.elkino.films.dto.FilmReduitDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/films")
@CrossOrigin
public class FilmController {

	private final FilmService filmService;
	private final ObjectMapper objectMapper;

	public FilmController(FilmService filmService, ObjectMapper objectMapper) {
		this.filmService = filmService;
		this.objectMapper = objectMapper;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FilmCompletDto save(@RequestBody Film film) {
		try {
			Film nouveauFilm = filmService.save(film);
			return objectMapper.convertValue(nouveauFilm, FilmCompletDto.class);

		} catch (PropertyValueException | DataIntegrityViolationException error) {
			throw new BadRequestExcepption("Le titre est obligatoire");
		} catch (Exception error) {
			throw new RuntimeException("Le film n'a pas pu être ajouté", error);
		}
	}

	@GetMapping("{id}")
	public FilmCompletDto findById(@PathVariable Integer id) {
		Film film = filmService.findById(id);
		return objectMapper.convertValue(film, FilmCompletDto.class);
	}

	@GetMapping
	public List<FilmReduitDto> findAll() {
		List<Film> films = filmService.findAll();
		return films.stream().map(film -> objectMapper.convertValue(film, FilmReduitDto.class)).toList();
	}

	@DeleteMapping("{id}")
	public void deleteById(@PathVariable Integer id) {
		filmService.deleteById(id);
	}

	@PutMapping
	public FilmCompletDto update(@RequestBody Film film) {
		Film entite = filmService.save(film);
		return objectMapper.convertValue(entite, FilmCompletDto.class);
	}

	@PostMapping("{id}/acteurs/{idActeur}")
	public FilmCompletDto addActeurById(@PathVariable Integer id, @PathVariable Integer idActeur) {
		Film entite = filmService.addActeurById(id, idActeur);
		return objectMapper.convertValue(entite, FilmCompletDto.class);
	}

	@PostMapping("{id}/acteurs")
	public FilmCompletDto addActeur(@PathVariable Integer id, @RequestBody Acteur acteur) {
		Film entite = filmService.addActeur(id, acteur);
		return objectMapper.convertValue(entite, FilmCompletDto.class);
	}

	@GetMapping("{id}/acteurs")
	public List<ActeurSansFilmDto> findActeurs(@PathVariable Integer id) {
		return filmService.findActeurs(id);
	}
}
