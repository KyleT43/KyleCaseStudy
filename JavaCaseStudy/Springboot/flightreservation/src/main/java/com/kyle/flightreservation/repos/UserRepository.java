package com.kyle.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyle.flightreservation.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
