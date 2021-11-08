package com.kyle.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyle.flightreservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
