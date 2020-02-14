package com.example.vehicle.dao;

import com.example.vehicle.model.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VehicleDataAccessService implements VehicleDao {

    private static List<Vehicle> database = new ArrayList<>();

    @Override
    public int insertVehicle(Vehicle vehicle) {
        database.add(vehicle);
        return 0;
    }

    @Override
    public List<Vehicle> getAllVehicle() {
        return database;
    }

    @Override
    public Optional<Vehicle> getVehicleByID(int id) {
        return database.stream().filter
                (vehicle -> vehicle.getId().equals(id)).findFirst();
    }

    @Override
    public int deleteVehicleByID(int id) {
        Optional<Vehicle> foundVehicle = getVehicleByID(id);
        if(foundVehicle.equals(null)) {
            return 1;
        }
        database.remove(foundVehicle.get());
        return 0;
    }

    @Override
    public int updateVehicle(int id, Vehicle vec) {
        Optional<Vehicle> foundVehicle = getVehicleByID(id);
        if(foundVehicle.equals(null)) {
            return 1;
        }
        int ind = database.indexOf(foundVehicle.get());
        if(ind != -1) {
            database.set(ind, vec);
            return 0;
        }

        return 1;

    }
}
