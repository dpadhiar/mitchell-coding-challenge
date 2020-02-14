package com.example.vehicle.api;

import com.example.vehicle.model.Vehicle;
import com.example.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/vehicle")
@RestController
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity addVehicle(@RequestBody Vehicle vehicle) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.addVehicle(vehicle));
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicle() {
        return ResponseEntity.ok().body(vehicleService.getAllVehicle());
    }

    @GetMapping("{id}")
    public ResponseEntity getVehicleByID(@PathVariable("id") int id) {

        Optional<Vehicle> vehicleOpt = vehicleService.getVehicleByID(id);
        if(!vehicleOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Vehicle with ID does not exist");
        }

        return ResponseEntity.ok().body(vehicleOpt.get());
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteVehicleByID(@PathVariable("id") int id) {
        int i = vehicleService.deleteVehicle(id);
        if(i == 1) {
            return ResponseEntity.badRequest().body("Vehicle with ID does not exist");
        }
        return ResponseEntity.ok().body("Vehicle deleted successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity updateVehicle(@PathVariable("id") int id, @RequestBody Vehicle vec) {
        int i = vehicleService.updateVehicle(id, vec);
        if(i == 1) {
            return ResponseEntity.badRequest().body("Vehicle with ID does not exist");
        }
        return ResponseEntity.ok().body("Vehicle updated successfully");
    }

}
