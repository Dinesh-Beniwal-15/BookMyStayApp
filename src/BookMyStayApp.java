import java.util.HashMap;
import java.util.Map;

/**
 * ===============================================================
 * ABSTRACT CLASS - Room
 * ===============================================================
 * Represents a generic hotel room.
 *
 * @version 4.0
 */
abstract class Room {

    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    public void displayRoomDetails() {
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + squareFeet + " sqft");
        System.out.println("Price per night: " + pricePerNight);
    }
}

/**
 * ===============================================================
 * CLASS - SingleRoom
 * ===============================================================
 */
class SingleRoom extends Room {

    public SingleRoom() {
        super(1, 250, 1500.0);
    }
}

/**
 * ===============================================================
 * CLASS - DoubleRoom
 * ===============================================================
 */
class DoubleRoom extends Room {

    public DoubleRoom() {
        super(2, 400, 2500.0);
    }
}

/**
 * ===============================================================
 * CLASS - SuiteRoom
 * ===============================================================
 */
class SuiteRoom extends Room {

    public SuiteRoom() {
        super(3, 750, 5000.0);
    }
}

/**
 * ===============================================================
 * CLASS - RoomInventory
 * ===============================================================
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * @version 4.0
 */
class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() {
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}

/**
 * ===============================================================
 * CLASS - RoomSearchService
 * ===============================================================
 *
 * Use Case 4: Room Search & Availability Check
 *
 * Provides read-only search functionality
 * for viewing available rooms.
 *
 * @version 4.0
 */
class RoomSearchService {

    public void searchAvailableRooms(
            RoomInventory inventory,
            Room singleRoom,
            Room doubleRoom,
            Room suiteRoom) {

        Map<String, Integer> availability = inventory.getRoomAvailability();

        System.out.println("Room Search\n");

        // Check Single Room
        if (availability.get("Single") > 0) {
            System.out.println("Single Room:");
            singleRoom.displayRoomDetails();
            System.out.println("Available: " + availability.get("Single") + "\n");
        }

        // Check Double Room
        if (availability.get("Double") > 0) {
            System.out.println("Double Room:");
            doubleRoom.displayRoomDetails();
            System.out.println("Available: " + availability.get("Double") + "\n");
        }

        // Check Suite Room
        if (availability.get("Suite") > 0) {
            System.out.println("Suite Room:");
            suiteRoom.displayRoomDetails();
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
 * Demonstrates how guests can search available rooms
 * without modifying inventory state.
 *
 * @version 4.0
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        // Create room objects
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Initialize centralized inventory
        RoomInventory inventory = new RoomInventory();

        // Create search service
        RoomSearchService searchService = new RoomSearchService();

        // Perform search
        searchService.searchAvailableRooms(
                inventory,
                singleRoom,
                doubleRoom,
                suiteRoom);
    }
}