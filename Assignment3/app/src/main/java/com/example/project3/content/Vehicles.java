package com.example.project3.content;

import java.util.ArrayList;
import java.util.List;

// NOTE: needs more variables and methods to accomodate the vehicle details
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

    // The ID for the index into make
    public static final String VEHICLE_ID_KEY = "item_id";
//    public static final String MAKE_MAKE_KEY = "item_make";

    private static final int COUNT = 5;

    public static class Vehicle {
        public final String vID;
        public final String vVehicle;

        // scope is public so we can manually initialize objects instead of hard coded
        // NOTE: may not work as this is sort done automatically
        public Vehicle(String vID, String vVehicle) {
            this.vID = vID;
            this.vVehicle = vVehicle;
        }

        public String getVehicleID() { return this.vID; }
        public String getVehicleVehicles() { return this.vVehicle; }

        // debug
        private static Vehicle createVehicleAtPosition(int position) {
            String newID;
            String newVehicle;
            switch(position) {
                case 0:
                    newID = "0";
                    newVehicle = "Toyota";
                    break;
                case 1:
                    newID = "1";
                    newVehicle = "Ferrari";
                    break;
                default:
                    newID = "9";
                    newVehicle = "Chevrolet";
            }
            return new Vehicle(newID, newVehicle);
        }
    }
}