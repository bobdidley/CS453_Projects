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
        public final String title;
        public final String details;


        private Vehicle( String newTitle, String newDetail) {
            this.title = newTitle;
            this.details = newDetail;
        }

    }


    private static void addItem(Vehicle item) { VEHICLE_ITEMS.add(item);
    }
    
    static{
        // Fill in array with vehicles
        for(int i=0; i<COUNT; i++){
            addItem(createVehicleAtPosition(i));
        }
    }

    private static Vehicle createVehicleAtPosition(int position) {
        String newTitle;
        String newDetail;
        switch (position) {
            case 0:
                newTitle = "Cry for a Shadow";
                newDetail = "Cry for a Shadow\n\nMany a Beatle fanatic " +
                        "started down the outtake road, like I did, with a " +
                        "first listen to this song. Originally titled “Beatle Bop” and recorded in a single session that yielded four songs (the other three featured Tony Sheridan with the Beatles as a backing band), “Cry for a Shadow” is an instrumental written by Lennon and Harrison, which makes it unique to this day. John Lennon plays rhythm guitar, George Harrison plays lead guitar, Paul McCartney plays bass, and Pete Best plays drums. The sessions were produced by Bert Kaempfert in Hamburg, Germany, during the Beatles’ second visit from April through July of 1961 to play in the Reeperbahn-section clubs.";
                break;
            case 1:
                newTitle = "My Bonnie - Ain’t She Sweet";
                newDetail = "My Bonnie - Ain’t She Sweet\n\nAt the same session, the Beatles played on “My Bonnie” (the first-ever single with Beatles playing), as the backing band for English singer Tony Sheridan, originally a member of the Jets. The popularity of this single in Liverpool brought the Beatles to the attention of Brian Epstein, who worked in the NEMS record store and tried to meet demand for the disc. John Lennon then sings a fine “Ain’t She Sweet” (his first-ever released vocal).";
                break;
            default:
                newTitle = "One After 909";
                newDetail = "One After 909\n\nA song recorded for the Let It Be album was actually worked on way back in the beginning, six years earlier. This take shows how they did it much more slowly, with an R&B feel to it.";
                break;
        }
        return new Vehicle (newTitle, newDetail);
    }



}