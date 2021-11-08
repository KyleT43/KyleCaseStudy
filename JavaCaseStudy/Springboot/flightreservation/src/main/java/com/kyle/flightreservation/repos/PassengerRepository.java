package com.kyle.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyle.flightreservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
