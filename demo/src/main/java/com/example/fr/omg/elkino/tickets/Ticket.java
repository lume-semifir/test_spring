package com.example.fr.omg.elkino.tickets;

import com.example.fr.omg.elkino.seances.Seance;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	@Column(nullable = false)
	String nomClient;
	@Column(nullable = false)
	Integer nombrePlaces;

	@ManyToOne
	@JoinColumn(name = "seance_id")
	private Seance seance;
}
