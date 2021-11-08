package com.kyle.flightreservation.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyle.flightreservation.dto.ReservationUpdateRequest;
import com.kyle.flightreservation.entities.Reservation;
import com.kyle.flightreservation.repos.ReservationRepository;
import com.kyle.flightreservation.util.PDFGenerator;

@RestController
@CrossOrigin
public class ReservationRestController {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);
	
	@Autowired
	ReservationRepository reservationRepository;

	
	
	@RequestMapping("/reservations/{id}")
	public Optional<Reservation> findReservation(@PathVariable("id") Long id) {
		LOGGER.info("Inside findReservation() for id: " +id );
		return reservationRepository.findById(id);
		
		
		
		
	}
	
	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
//		Reservation reservation = reservationRepository.getById(request.getId());
//		reservation.setNumberOfBags(request.getNumberOfBags());
//		reservation.setCheckedIn(request.getCheckedIn());
		LOGGER.info("Inside updateReservation() for: " +request );
		Reservation reservation = reservationRepository.findById(request.getId()).get();
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.getCheckedIn());


		LOGGER.info("Saving Reservation");
		return reservationRepository.save(reservation);
		
	}
	
}
