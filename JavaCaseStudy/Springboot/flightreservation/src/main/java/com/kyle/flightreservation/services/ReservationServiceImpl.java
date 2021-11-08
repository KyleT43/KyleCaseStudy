package com.kyle.flightreservation.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kyle.flightreservation.controllers.ReservationController;
import com.kyle.flightreservation.dto.ReservationRequest;
import com.kyle.flightreservation.entities.Flight;
import com.kyle.flightreservation.entities.Passenger;
import com.kyle.flightreservation.entities.Reservation;
import com.kyle.flightreservation.repos.FlightRepository;
import com.kyle.flightreservation.repos.PassengerRepository;
import com.kyle.flightreservation.repos.ReservationRepository;
import com.kyle.flightreservation.util.EmailUtil;
import com.kyle.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Value("${com.kyle.flightreservation.itinerary.dirpath}")
//	private String ITINERARY_DIR = "C:\\Users\\P10262PH1\\Documents\\reservations\\reservation";
	private String ITINERARY_DIR;
	
	
	@Autowired		
	 FlightRepository flightRepository;
	@Autowired
	PassengerRepository passengerRepository;
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	@Autowired
	EmailUtil emailUtil;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);
	
	@Override
	public Reservation bookflight(ReservationRequest request) {
		
		LOGGER.info("Inside bookFlight()");
		
		//make payment
		
		Long flightId = request.getFlightId();
		LOGGER.info("Fetching Flight for flight id:" + flightId);
		Flight flight = flightRepository.findById(flightId).get();
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		
		LOGGER.info("Saving the passenger"+ passenger);
		
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		
		LOGGER.info("Saving the reservation"+reservation);
		
		Reservation savedReservation = reservationRepository.save(reservation);
		
		String filepath = ITINERARY_DIR+savedReservation.getId()+".pdf";
		
		LOGGER.info("Generating the Itinerary");
		
		pdfGenerator.generateItinerary(savedReservation, filepath);
		
		LOGGER.info("Emailing the Itinerary");
		
		emailUtil.sendItinerary(passenger.getEmail(), filepath);
		
		return savedReservation;
	}

}
