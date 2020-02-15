package com.example.vehicle.service;

import com.example.vehicle.dao.VehicleDao;
import com.example.vehicle.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Sends logic to our database to retrieve objects.
 *
 * @author dillenpadhiar
 */
@Service
public class VehicleService {

    // instance of data access object
    private final VehicleDao vehicleDao;

    /**
     * Constructor of VehicleService
     *
     * @param vehicleDao - object which functions are called on for CRUD ops
     */
    @Autowired
    public VehicleService(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    /**
     * Inserts vehicle object into our database.
     *
     * @param vehicle - vehicle to be inserted
     *
     * @return 0 to signal success, 1 for failure
     */
    public int addVehicle(Vehicle vehicle) {
        return vehicleDao.insertVehicle(vehicle);
    }

    /**
     * Retrieves all vehicles in our database.
     *
     * @return list of all vehicles objects in database
     */
    public List<Vehicle> getAllVehicle() {
        return vehicleDao.getAllVehicle();
    }

    /**
     * Retrieves vehicle with specific ID in database.
     *
     * @param id - identifier of vehicle to be retrieved
     *
     * @return vehicle object if found or error if not present in database
     */
    public Optional<Vehicle> getVehicleByID(int id) {
        return vehicleDao.getVehicleByID(id);
    }

    /**
     * Removes vehicle with specific ID in database.
     *
     * @param id - identifier of vehicle to be deleted
     *
     * @return 0 to signal success, 1 for failure
     */
    public int deleteVehicle(int id) {
        return vehicleDao.deleteVehicleByID(id);
    }

    /**
     * Updates the fields of a vehicle in the database.
     *
     * @param id - identifier of vehicle to be updated
     * @param vec - vehicle object with updated fields
     *
     * @return 0 to signal success, 1 for failure
     */
    public int updateVehicle(int id, Vehicle vec) {
        return vehicleDao.updateVehicle(id, vec);
    }
}
