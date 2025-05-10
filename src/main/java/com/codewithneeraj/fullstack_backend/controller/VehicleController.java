package com.codewithneeraj.fullstack_backend.controller;

import com.codewithneeraj.fullstack_backend.model.User;
import com.codewithneeraj.fullstack_backend.model.Vehicle;
import com.codewithneeraj.fullstack_backend.repository.UserRepository;
import com.codewithneeraj.fullstack_backend.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "http://localhost:3000") // if frontend React app is running
public class VehicleController {

    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;

    @Autowired
    public VehicleController(VehicleRepository vehicleRepository, UserRepository userRepository) {
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
    }

    // 1️⃣ Get all vehicles
    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // 2️⃣ Register (create) new vehicle and assign to user
    @PostMapping("/register/{userId}")
    public String registerVehicle(@RequestBody Vehicle vehicle, @PathVariable Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            vehicle.setUser(userOptional.get());
            vehicleRepository.save(vehicle);
            return "Vehicle registered successfully!";
        } else {
            return "User not found. Vehicle registration failed.";
        }
    }

    // 3️⃣ Get vehicles for a specific user
    @GetMapping("/user/{userId}")
    public List<Vehicle> getVehiclesByUser(@PathVariable Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(User::getVehicles).orElse(null);
    }

}