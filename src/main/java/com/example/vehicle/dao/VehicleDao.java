package com.example.vehicle.dao;

import com.example.vehicle.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleDao {

    int insertVehicle(Vehicle vehicle);

    List<Vehicle> getAllVehicle();

    Optional<Vehicle> getVehicleByID(int id);

    int deleteVehicleByID(int id);

    int updateVehicle(int id, Vehicle vec);

}
