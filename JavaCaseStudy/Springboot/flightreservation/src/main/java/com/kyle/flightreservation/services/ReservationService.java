package com.kyle.flightreservation.services;

import com.kyle.flightreservation.dto.ReservationRequest;
import com.kyle.flightreservation.entities.Reservation;

public interface ReservationService {
	
	public Reservation bookflight(ReservationRequest request);

}
