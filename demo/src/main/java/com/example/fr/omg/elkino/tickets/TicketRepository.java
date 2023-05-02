package com.example.fr.omg.elkino.tickets;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	public List<Ticket> findBySeanceId(Integer id);
}
