package com.example.vehicle.service;

import com.example.vehicle.dao.VehicleDao;
import com.example.vehicle.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleDao vehicleDao;

    @Autowired
    public VehicleService(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    public int addVehicle(Vehicle vehicle) {
        return vehicleDao.insertVehicle(vehicle);
    }

    public List<Vehicle> getAllVehicle() {
        return vehicleDao.getAllVehicle();
    }

    public Optional<Vehicle> getVehicleByID(int id) {
        return vehicleDao.getVehicleByID(id);
    }

    public int deleteVehicle(int id) {
        return vehicleDao.deleteVehicleByID(id);
    }

    public int updateVehicle(int id, Vehicle vec) {
        return vehicleDao.updateVehicle(id, vec);
    }
}
