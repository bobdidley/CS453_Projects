package com.example.project3.content;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class Vehicles {

    /**
     * An array of sample (placeholder) items.
     */
    public static final List<Vehicle> VEHICLE_ITEMS = new ArrayList<>();

    // The ID for the index into vehicle
    public static final String VEHICLE_ID_KEY = "item_id";

    private static final int COUNT = 5;

    public static class Vehicle {
        public String vID = "";
        public String vMakes = "";

        public Vehicle(){}

        public Vehicle(String vID, String vMakes) {
            this.vID = vID;
            this.vMakes = vMakes;
        }


        public void setVehicleID(String vID){ this.vID = vID;}
        public void setVehicleMakes(String vMakes){this.vMakes = vMakes;}

        public String getVehicleID(){return this.vID;}
        public String getVehicleMakes(){return this.vMakes;}

    }

}