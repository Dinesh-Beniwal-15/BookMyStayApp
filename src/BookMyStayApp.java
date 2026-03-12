import java.util.HashMap;
import java.util.Map;

/**
 * ===============================================================
 * CLASS - RoomSearchService
 * ===============================================================
 *
 * Use Case 4: Room Search & Availability Check
 *
 * Description:
 * This class provides search functionality
 * for guests to view available rooms.
 *
 * No inventory mutation or booking logic
 * is performed in this class.
 *
 * @version 4.0
 */

class RoomSearchService {

    /**
     * Displays available rooms
     *
     * @param availability map storing room availability
     */
    public void searchAvailableRooms(Map<String, Integer> availability) {

        System.out.println("Room Search\n");

        // Check Single Room
        if (availability.get("Single") > 0) {
            System.out.println("Single Room:");
            System.out.println("Beds: 1");
            System.out.println("Size: 250 sqft");
            System.out.println("Price per night: 1500.0");
            System.out.println("Available: " + availability.get("Single"));
            System.out.println();
        }

        // Check Double Room
        if (availability.get("Double") > 0) {
            System.out.println("Double Room:");
            System.out.println("Beds: 2");
            System.out.println("Size: 400 sqft");
            System.out.println("Price per night: 2500.0");
            System.out.println("Available: " + availability.get("Double"));
            System.out.println();
        }

        // Check Suite Room
        if (availability.get("Suite") > 0) {
            System.out.println("Suite Room:");
            System.out.println("Beds: 3");
            System.out.println("Size: 750 sqft");
            System.out.println("Price per night: 5000.0");
            System.out.println("Available: " + availability.get("Suite"));
        }
    }
}


/**
 * ===============================================================
 * MAIN CLASS - BookMyStayApp
 * ===============================================================
 *
 * Use Case 4: Room Search & Availability Check
 *
 * Demonstrates how guests can view available rooms
 * without modifying system state.
 *
 * @version 4.0
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        // Simulated inventory data
        Map<String, Integer> availability = new HashMap<>();

        availability.put("Single", 5);
        availability.put("Double", 3);
        availability.put("Suite", 2);

        // Create search service
        RoomSearchService searchService = new RoomSearchService();

        // Perform room search
        searchService.searchAvailableRooms(availability);
    }
}
