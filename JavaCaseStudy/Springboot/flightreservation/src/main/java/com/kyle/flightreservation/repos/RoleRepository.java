package com.kyle.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyle.flightreservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
