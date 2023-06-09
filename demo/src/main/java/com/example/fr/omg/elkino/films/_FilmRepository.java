package com.example.fr.omg.elkino.films;

import java.util.List;

import jakarta.persistence.EntityManager;

public class _FilmRepository {
	private final EntityManager entityManager; // Gestionnaire d'entités de JPA

	public _FilmRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Sauvegarde un film dans la base de données
	 * 
	 * @param film à sauvegarder
	 * @return le film sauvegardé avec son id
	 */
	public Film save(Film film) {
		// INSERT INTO film (titre, date_sortie, duree, synopsis) VALUES (?, ?, ?, ?)
		entityManager.persist(film); // Persiste l'entité dans la base de données
		entityManager.flush(); // Synchro de l'instance avec la base de données (et ajoute l'ID)
		return film;
	}

	/**
	 * Récupère un film par son id
	 * 
	 * @param id du film
	 * @return le film
	 */
	public Film findById(Integer id) {
		// SELECT * FROM film WHERE id = id
		return entityManager.find(Film.class, id); // Retourne l'entité correspondant à l'id
	}

	/**
	 * Récupère tous les films
	 * 
	 * @return la liste des films
	 */
	public List<Film> findAll() {
		// SELECT * FROM film
		return entityManager.createQuery("SELECT f FROM Film f", Film.class).getResultList();
	}

	/**
	 * Met à jour un film dans la bdd
	 * 
	 * @param film à mettre à jour
	 * @return le film mis à jour
	 */
	public Film update(Film film) {
		// UPDATE film SET titre = ?, date_sortie = ?, duree = ?, synopsis = ? WHERE id
		// = ?
		return entityManager.merge(film);
	}

	/**
	 * Supprime un film de la bdd
	 * 
	 * @param id du film à supprimer
	 */
	public void deleteById(Integer id) {
		// DELETE FROM film WHERE id = ?
		entityManager.remove(findById(id));
	}
}
