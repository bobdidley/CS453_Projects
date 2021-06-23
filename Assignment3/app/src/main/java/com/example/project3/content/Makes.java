package com.example.project3.content;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class Makes {

    /**
     * An array of sample (placeholder) items.
     */
    public static final List<Make> MAKE_ITEMS = new ArrayList<>();

    // The ID for the index into make
    public static final String MAKE_ID_KEY = "item_id";
//    public static final String MAKE_MAKE_KEY = "item_make";

    private static final int COUNT = 5;

    public static class Make {
        public final String mID;
        public final String mMake;

        // scope is public so we can manually initialize objects instead of hard coded
        // NOTE: may not work as this is sort done automatically
        public Make(String mID, String mMakes) {
            this.mID = mID;
            this.mMake = mMakes;
        }

        public String getMakeID() { return this.mID; }
        public String getMakeMakes() { return this.mMake; }

        // debug
        private static Make createMakeAtPosition(int position) {
            String newID;
            String newMake;
            switch(position) {
                case 0:
                    newID = "0";
                    newMake = "Toyota";
                    break;
                case 1:
                    newID = "1";
                    newMake = "Ferrari";
                    break;
                default:
                    newID = "9";
                    newMake = "Chevrolet";
            }
            return new Make(newID, newMake);
        }
    }

}