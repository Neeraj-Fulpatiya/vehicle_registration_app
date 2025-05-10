package com.codewithneeraj.fullstack_backend.repository;

import com.codewithneeraj.fullstack_backend.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
