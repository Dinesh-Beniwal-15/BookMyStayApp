import java.util.*;

/**
 * ===============================================================
 * CLASS - RoomInventory
 * ===============================================================
 *
 * Maintains room availability.
 *
 * @version 10.0
 */

class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    public void updateAvailability(String roomType, int newCount) {
        roomAvailability.put(roomType, newCount);
    }
}


/**
 * ===============================================================
 * CLASS - CancellationService
 * ===============================================================
 *
 * Handles booking cancellation and rollback.
 *
 * @version 10.0
 */

class CancellationService {

    /** Stack storing recently released room IDs */
    private Stack<String> releasedRoomIds;

    /** Maps reservation ID -> room type */
    private Map<String, String> reservationRoomTypeMap;

    public CancellationService() {
        releasedRoomIds = new Stack<>();
        reservationRoomTypeMap = new HashMap<>();
    }

    /**
     * Registers a confirmed booking
     */
    public void registerBooking(String reservationId, String roomType) {
        reservationRoomTypeMap.put(reservationId, roomType);
    }

    /**
     * Cancels booking and restores inventory
     */
    public void cancelBooking(String reservationId, RoomInventory inventory) {

        if (!reservationRoomTypeMap.containsKey(reservationId)) {
            System.out.println("Invalid cancellation request. Reservation not found.");
            return;
        }

        String roomType = reservationRoomTypeMap.get(reservationId);

        // Push to rollback stack
        releasedRoomIds.push(reservationId);

        // Restore inventory
        Map<String, Integer> availability = inventory.getRoomAvailability();
        int current = availability.get(roomType);
        inventory.updateAvailability(roomType, current + 1);

        // Remove booking record
        reservationRoomTypeMap.remove(reservationId);

        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
    }

    /**
     * Displays rollback history
     */
    public void showRollbackHistory() {

        System.out.println("\nRollback History (Most Recent First):");

        if (releasedRoomIds.isEmpty()) {
            System.out.println("No cancellations yet.");
            return;
        }

        for (int i = releasedRoomIds.size() - 1; i >= 0; i--) {
            System.out.println("Released Reservation ID: " + releasedRoomIds.get(i));
        }
    }
}


/**
 * ===============================================================
 * MAIN CLASS - BookMyStayApp
 * ===============================================================
 *
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * @version 10.0
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Booking Cancellation");

        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService();

        // Simulate confirmed booking
        String reservationId = "Single-1";
        service.registerBooking(reservationId, "Single");

        // Cancel booking
        service.cancelBooking(reservationId, inventory);

        // Show rollback history
        service.showRollbackHistory();

        // Display updated inventory
        int updated = inventory.getRoomAvailability().get("Single");
        System.out.println("\nUpdated Single Room Availability: " + updated);
    }
}