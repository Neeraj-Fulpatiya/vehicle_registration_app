package com.codewithneeraj.fullstack_backend.service;

import com.codewithneeraj.fullstack_backend.model.Vehicle;
import com.codewithneeraj.fullstack_backend.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        return vehicleOptional.orElse(null);
    }

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        if (vehicleOptional.isPresent()) {
            Vehicle existingVehicle = vehicleOptional.get();
            existingVehicle.setRegistrationNumber(updatedVehicle.getRegistrationNumber());
            existingVehicle.setBrand(updatedVehicle.getBrand());
            existingVehicle.setModel(updatedVehicle.getModel());
//            existingVehicle.setManufactureYear(updatedVehicle.getManufactureYear());
            // Add more setters if you have more fields
            return vehicleRepository.save(existingVehicle);
        } else {
            return null;
        }
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }


}