package com.example.vehicle.dao;

import com.example.vehicle.model.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implements CRUD methods which access the database of vehicles.
 *
 * @author dillenpadhiar
 */

// annotation to make show this class functions as the DAO
@Repository
public class VehicleDataAccessService implements VehicleDao {

    // initialize our database
    private static List<Vehicle> database = new ArrayList<>();

    /**
     * Inserts vehicle into our database.
     *
     * @param vehicle - object to be inserted
     * @return 0 to signal successful addition
     */
    @Override
    public int insertVehicle(Vehicle vehicle) {
        database.add(vehicle);
        return 0;
    }

    /**
     * Gets all vehicles in our database.
     *
     * @return Returns a list of all vehicles in database.
     */
    @Override
    public List<Vehicle> getAllVehicle() {
        return database;
    }

    /**
     * Gets a specific vehicle using its ID.
     *
     * @param id - integer id of the vehicle to be found
     *
     * @return vehicle if one is found with id, error if not
     */
    @Override
    public Optional<Vehicle> getVehicleByID(int id) {
        // turns array in stream and filters to find vehicle with id
        return database.stream().filter
                (vehicle -> vehicle.getId().equals(id)).findFirst();
    }

    /**
     * Deletes a specific vehicle in the database.
     *
     * @param id - integer id of the vehicle to be deleted
     *
     * @return 0 if vehicle deleted successfully, 1 if not found
     */
    @Override
    public int deleteVehicleByID(int id) {
        Optional<Vehicle> foundVehicle = getVehicleByID(id);
        // checks that a vehicle was found with id
        if(foundVehicle.equals(null)) {
            return 1;
        }

        database.remove(foundVehicle.get());
        return 0;
    }

    /**
     * Updates a vehicle within the database.
     *
     * @param id - integer id of vehicle to be updated
     * @param vec - updated vehicle to be "added"
     * @return 0 if successful, 1 if not found
     */
    @Override
    public int updateVehicle(int id, Vehicle vec) {
        Optional<Vehicle> foundVehicle = getVehicleByID(id);
        // checks that a vehicle was found
        if(foundVehicle.equals(null)) {
            return 1;
        }
        int ind = database.indexOf(foundVehicle.get());
        // checks that vehicle does exist in database by indice check
        if(ind != -1) {
            database.set(ind, vec);
            return 0;
        }

        return 1;

    }
}
