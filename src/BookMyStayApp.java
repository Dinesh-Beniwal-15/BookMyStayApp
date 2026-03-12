import java.util.HashMap;
import java.util.Map;

/**
 * ===============================================================
 * CLASS - RoomInventory
 * ===============================================================
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * Description:
 * This class acts as the single source of truth
 * for room availability in the hotel.
 *
 * @version 3.1
 */

class RoomInventory {

    /**
     * Stores available room count for each room type
     *
     * Key   -> Room type name
     * Value -> Available room count
     */
    private Map<String, Integer> roomAvailability;

    /**
     * Constructor initializes the inventory
     * with default availability values
     */
    public RoomInventory() {

        roomAvailability = new HashMap<>();

        initializeInventory();
    }

    /**
     * Initializes room availability data
     */
    private void initializeInventory() {

        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    /**
     * Returns the availability map
     */
    public Map<String, Integer> getRoomAvailability() {

        return roomAvailability;
    }

    /**
     * Updates availability for a specific room type
     */
    public void updateAvailability(String roomType, int count) {

        roomAvailability.put(roomType, count);
    }
}


/**
 * ===============================================================
 * MAIN CLASS - BookMyStayApp
 * ===============================================================
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * Demonstrates how room availability is managed
 * using a centralized HashMap.
 *
 * @version 3.1
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Hotel Room Inventory Status\n");

        RoomInventory inventory = new RoomInventory();

        Map<String, Integer> availability = inventory.getRoomAvailability();

        System.out.println("Single Room Available Rooms: "
                + availability.get("Single"));

        System.out.println("Double Room Available Rooms: "
                + availability.get("Double"));

        System.out.println("Suite Room Available Rooms: "
                + availability.get("Suite"));
    }
}