package com.example.vehicle;

import com.example.vehicle.model.Vehicle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.*;

/**
 * Tester class to check functionality of service.
 *
 * @author dillenpadhiar
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = VehicleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VehicleControllerTest {

    // restTemplate to model service and send requests
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    /**
     * Generates port to test service on.
     *
     * @return string of location to send requests
     */
    private String getRootUrl() {
        return "http://localhost:" + port + "/api/v1";
    }

    /**
     * Sets up database before each test is run.
     */
    @Before
    public void Before() {

        int[] vecID = {11111, 11112, 11113, 11114, 11115, 11116, 11117, 11118};
        int[] vecYear = {1998, 2004, 2018, 2020, 2006, 1983, 2015, 2019};
        String[] vecMake = {"Toyota", "Honda", "Tesla", "Nissan", "Volkswagen",
                "BMW", "Chevrolet", "Ford"};
        String[] vecModel = {"Corolla", "Civic", "Model S", "Rogue", "Jetta",
                "315i", "Silverado", "F-150"};
        for(int i = 0; i < vecID.length; i++) {
            Vehicle vec = new Vehicle(vecID[i], vecYear[i], vecMake[i], vecModel[i]);
            restTemplate.postForEntity(getRootUrl() + "/vehicle", vec, String.class);
        }
    }

    /**
     * Checks that get request for all vehicles returns objects and is not null.
     */
    @Test
    public void TestGetAllVehicles() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null,headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/vehicle",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    /**
     * Checks that specific vehicle is returned by get request.
     */
    @Test
    public void TestGetVehicleByID() {
        Vehicle testVec = restTemplate.getForObject(getRootUrl() + "/vehicle/11114", Vehicle.class);
        assertNotNull(testVec);
        assertEquals((long) testVec.getId(), (long) 11114);
        assertEquals((long) testVec.getYear(), (long) 2020);
        assertEquals(testVec.getMake(), "Nissan");
        assertEquals(testVec.getModel(), "Rogue");
    }

    /**
     * Checks that post requests to create vehicles are functioning as intended.
     */
    @Test
    public void TestCreateVehicle() {
        Vehicle newVec = new Vehicle(11119, 2014, "Audi", "A7");
        ResponseEntity<String> postResponse = restTemplate.postForEntity(getRootUrl() + "/vehicle",
                newVec, String.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    /**
     * Checks that put requests to update vehicles are functioning as intended.
     */
    @Test
    public void TestUpdateVehicle() {
        int vecToUpdateID = 11115;
        Vehicle vec = restTemplate.getForObject(getRootUrl() + "/vehicle/" + vecToUpdateID, Vehicle.class);
        vec.setMake("Mazda");
        vec.setModel("Miata");
        vec.setYear(2017);
        restTemplate.put(getRootUrl() + "/vehicle/" + vecToUpdateID, vec);
        Vehicle updatedVec = restTemplate.getForObject(getRootUrl() + "/vehicle/" + vecToUpdateID, Vehicle.class);
        assertEquals((long) updatedVec.getId(), (long) 11115);
        assertEquals((long) updatedVec.getYear(), (long) 2017);
        assertEquals(updatedVec.getMake(), "Mazda");
        assertEquals(updatedVec.getModel(), "Miata");
    }

    /**
     * Checks that delete requests to remove vehicles are functioning as intended.
     */
    @Test
    public void TestDeleteVehicle() {
        int vecToDeleteID = 11118;
        Vehicle vec = restTemplate.getForObject(getRootUrl() + "/vehicle/" + vecToDeleteID, Vehicle.class);
        assertNotNull(vec);
        restTemplate.delete(getRootUrl() + "/vehicle/" + vecToDeleteID, Vehicle.class);
        try {
            vec = restTemplate.getForObject(getRootUrl() + "/vehicle/" + vecToDeleteID, Vehicle.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
