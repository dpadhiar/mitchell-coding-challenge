package com.example.vehicle.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Defining the vehicle entity of our database.
 *
 * @author dillenpadhiar
 */
public class Vehicle {

        // variables of a vehicle object
        public Integer id;
        public Integer year;
        public String make;
        public String model;

        /**
         * Constructor of vehicle.
         *
         * @param id - unique id to be set for vehicle
         * @param year - year vehicle was produced
         * @param make - name of manufacturer of vehicle
         * @param model - type or build of vehicle
         */
        public Vehicle(@JsonProperty("id") Integer id,
                       @JsonProperty("year") Integer year,
                       @JsonProperty("make") String make,
                       @JsonProperty("model") String model) {
                this.id = id;
                this.year = year;
                this.make = make;
                this.model = model;
        }

        /**
         * Empty constructor.
         */
        public Vehicle(){}

        /**
         * Returns id.
         *
         * @return integer id of vehicle
         */
        public Integer getId() {
                return id;
        }

        /**
         * Sets id of vehicle.
         *
         * @param id - int to set id of vehicle to
         */
        public void setId(Integer id) {
                this.id = id;
        }

        /**
         * Return year.
         *
         * @return integer year of vehicle
         */
        public Integer getYear() {
                return year;
        }

        /**
         * Sets year of vehicle.
         *
         * @param year - int to set year of vehicle to
         */
        public void setYear(Integer year) {
                this.year = year;
        }

        /**
         * Returns make of vehicle.
         *
         * @return name of manufacturer of the vehicle
         */
        public String getMake() {
                return make;
        }

        /**
         * Sets make of vehicle.
         *
         * @param make - name of manufacturer of vehicle
         */
        public void setMake(String make) {
                this.make = make;
        }

        /**
         * Returns model of vehicle.
         *
         * @return name of model of vehicle
         */
        public String getModel() {
                return model;
        }

        /**
         * Sets model of vehicle.
         *
         * @param model - name of model of vehicle
         */
        public void setModel(String model) {
                this.model = model;
        }


}
