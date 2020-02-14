package com.example.vehicle.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vehicle {

        public Integer id;
        public Integer year;
        public String make;
        public String model;

        public Vehicle(@JsonProperty("id") Integer id,
                       @JsonProperty("year") Integer year,
                       @JsonProperty("make") String make,
                       @JsonProperty("model") String model) {
                this.id = id;
                this.year = year;
                this.make = make;
                this.model = model;
        }

        public Vehicle(){}

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public Integer getYear() {
                return year;
        }

        public void setYear(Integer year) {
                this.year = year;
        }

        public String getMake() {
                return make;
        }

        public void setMake(String make) {
                this.make = make;
        }

        public String getModel() {
                return model;
        }

        public void setModel(String model) {
                this.model = model;
        }


}
