package com.example.fr.omg.elkino.seances;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.fr.omg.elkino.salle.Salle;
import com.example.fr.omg.elkino.salle.SalleService;
import com.example.fr.omg.elkino.tickets.Ticket;
import com.example.fr.omg.elkino.tickets.TicketService;

@Service
public class SeanceService {
	private final SeanceRepository seanceRepository;
	private final SalleService salleService;
	private final TicketService ticketService;

	public SeanceService(SeanceRepository seanceRepository, SalleService salleService, TicketService ticketService) {
		this.seanceRepository = seanceRepository;
		this.salleService = salleService;
		this.ticketService = ticketService;
	}

	public Seance save(Seance nouvelleSeance) {
		Salle salle = salleService.findById(nouvelleSeance.getSalle().getId());
		nouvelleSeance.setPlacesDispo(salle.getPlaces());
		return seanceRepository.save(nouvelleSeance);
	}

	public Seance update(Seance seance) {
		return seanceRepository.save(seance);
	}

	public List<Seance> findAll() {
		return seanceRepository.findAll();
	}

	public Seance findById(Integer id) {
		return seanceRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Film non trouvé"));
	}

	public void deleteById(Integer id) {
		seanceRepository.deleteById(id);
	}

	public List<Seance> findByDate(String date) {
		LocalDate dateRecherche = LocalDate.parse(date);
		return seanceRepository.findByDate(dateRecherche);
	}

	public Ticket reserver(Integer id, Ticket ticket) {
		Seance seance = findById(id);
		if (seance.getPlacesDispo() < ticket.getNombrePlaces()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pas assez de places disponibles");
		} else {
			seance.setPlacesDispo(seance.getPlacesDispo() - ticket.getNombrePlaces());
			update(seance);
			return ticketService.save(ticket);
		}
	}

	public List<Ticket> getTickets(Integer id) {
		return ticketService.findBySeanceId(id);
	}

}
