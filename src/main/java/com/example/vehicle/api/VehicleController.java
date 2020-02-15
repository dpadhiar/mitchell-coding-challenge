package com.example.vehicle.api;

import com.example.vehicle.model.Vehicle;
import com.example.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 * Handles requests for our database and sends them to our service.
 *
 * @author dillenpadhiar
 */

// outlines path of requests for this service
@RequestMapping("/api/v1/vehicle")
@RestController
public class VehicleController {

    // instance of service to be called on
    private final VehicleService vehicleService;

    /**
     * Constructor of vehicle controller.
     *
     * @param vehicleService - object to send requests to
     */
    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * Post request to add vehicles to database.
     *
     * @param vehicle - vehicle object to be added to database
     *
     * @return Status 201 to show object has been created
     */
    @PostMapping
    public ResponseEntity addVehicle(@RequestBody Vehicle vehicle) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.addVehicle(vehicle));
    }

    /**
     * Get request to read all vehicles in database.
     *
     * @return list of all vehicles in database
     */
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicle() {
        return ResponseEntity.ok().body(vehicleService.getAllVehicle());
    }

    /**
     * Get request to read specific vehicle in database.
     *
     * @param id - integer identifier of vehicle
     *
     * @return data of vehicle in success, error message if not found
     */
    @GetMapping("{id}")
    public ResponseEntity getVehicleByID(@PathVariable("id") int id) {

        // get vehicle from database and check that its not null
        Optional<Vehicle> vehicleOpt = vehicleService.getVehicleByID(id);
        if(!vehicleOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Vehicle with ID does not exist");
        }

        // ok status and data of vehicle returned
        return ResponseEntity.ok().body(vehicleOpt.get());
    }

    /**
     * Delete request to remove specific vehicle in database.
     *
     * @param id - integer identifier of vehicle
     *
     * @return body showing vehicle was deleted or not found
     */
    @DeleteMapping("{id}")
    public ResponseEntity deleteVehicleByID(@PathVariable("id") int id) {
        // call to remove vehicle from database
        int i = vehicleService.deleteVehicle(id);
        // if 1 is returned, vehicle wasn't found
        if(i == 1) {
            return ResponseEntity.badRequest().body("Vehicle with ID does not exist");
        }
        return ResponseEntity.ok().body("Vehicle deleted successfully");
    }

    /**
     * Put request to updated specific vehicle in database.
     *
     * @param id - integer identifier of vehicle to be updated
     * @param vec - updated vehicle to overwrite previous
     *
     * @return body showing vehicle was updated or not found
     */
    @PutMapping("{id}")
    public ResponseEntity updateVehicle(@PathVariable("id") int id, @RequestBody Vehicle vec) {
        // call to update vehicle
        int i = vehicleService.updateVehicle(id, vec);
        // if 1 returned, vehicle wasn't found
        if(i == 1) {
            return ResponseEntity.badRequest().body("Vehicle with ID does not exist");
        }
        return ResponseEntity.ok().body("Vehicle updated successfully");
    }

}
