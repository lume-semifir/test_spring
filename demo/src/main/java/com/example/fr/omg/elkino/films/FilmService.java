package com.example.fr.omg.elkino.films;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.fr.omg.elkino.acteurs.Acteur;
import com.example.fr.omg.elkino.acteurs.ActeurService;
import com.example.fr.omg.elkino.acteurs.dto.ActeurSansFilmDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FilmService {

	private final FilmRepository filmRepository;
	private final ActeurService acteurService;
	private final ObjectMapper objectMapper;

	public FilmService(FilmRepository filmRepository, ActeurService acteurService, ObjectMapper objectMapper) {
		this.filmRepository = filmRepository;
		this.acteurService = acteurService;
		this.objectMapper = objectMapper;
	}

	/**
	 * Sauvegarde un film dans la base de données. Si le film n'a pas d'id, il est
	 * créé, sinon il est mis à jour.
	 *
	 * @param film
	 */
	public Film save(Film film) {
		return filmRepository.save(film);
	}

	/**
	 * Récupère un film par son id
	 *
	 * @param id
	 * @return le film recherché
	 * @throws ResponseStatusException si le film n'existe pas
	 */
	public Film findById(Integer id) {
		// orElseThrow : si l'objet est vide, on lance une exception
		// Retourne automatiquement une 404
		return filmRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Film non trouvé"));

	}

	/**
	 * Récupère tous les films
	 *
	 * @return
	 */
	public List<Film> findAll() {
		return filmRepository.findAll();
	}

	/**
	 * Supprime un film de la bdd Si l'ID n'existe pas, ne fait rien
	 * 
	 * @param id
	 */
	public void deleteById(Integer id) {
		filmRepository.deleteById(id);
	}

	public Film addActeurById(Integer id, Integer idActeur) {
		Acteur acteur = new Acteur();
		acteur.setId(idActeur);
		return this.addActeur(id, acteur);
	}

	public Film addActeur(Integer id, Acteur acteur) {
		Film film = filmRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Film non trouvé"));
		Acteur acteurSauvegarde = acteurService.findOrInsertActeur(acteur);
		film.getActeurs().add(acteurSauvegarde);
		filmRepository.save(film);
		return findById(id);
	}

	public List<ActeurSansFilmDto> findActeurs(Integer id) {
		return filmRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Film non trouvé")).getActeurs()
				.stream().map(acteur -> objectMapper.convertValue(acteur, ActeurSansFilmDto.class)).toList();
	}

}
