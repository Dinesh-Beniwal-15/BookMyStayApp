import java.util.*;

/**
 * ===============================================================
 * CLASS - InvalidBookingException
 * ===============================================================
 *
 * Custom exception for invalid booking scenarios.
 *
 * @version 9.0
 */

class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
    }
}


/**
 * ===============================================================
 * CLASS - RoomInventory
 * ===============================================================
 *
 * Minimal inventory for validation purposes.
 *
 * @version 9.0
 */

class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        roomAvailability.put("Single", 2);
        roomAvailability.put("Double", 1);
        roomAvailability.put("Suite", 1);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }
}


/**
 * ===============================================================
 * CLASS - ReservationValidator
 * ===============================================================
 *
 * Validates booking input.
 *
 * @version 9.0
 */

class ReservationValidator {

    public void validate(
            String guestName,
            String roomType,
            RoomInventory inventory
    ) throws InvalidBookingException {

        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty");
        }

        Map<String, Integer> availability = inventory.getRoomAvailability();

        if (!availability.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type selected");
        }

        if (availability.get(roomType) <= 0) {
            throw new InvalidBookingException("No rooms available for selected type");
        }
    }
}


/**
 * ===============================================================
 * CLASS - BookingRequestQueue
 * ===============================================================
 */

class BookingRequestQueue {

    private Queue<String> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(String request) {
        queue.offer(request);
    }
}


/**
 * ===============================================================
 * MAIN CLASS - BookMyStayApp
 * ===============================================================
 *
 * Use Case 9: Error Handling & Validation
 *
 * Demonstrates input validation and exception handling.
 *
 * @version 9.0
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Booking Validation");

        Scanner scanner = new Scanner(System.in);

        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        try {

            // Take user input
            System.out.print("Enter Guest Name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter Room Type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            // Validate input
            validator.validate(guestName, roomType, inventory);

            // If valid, add to queue
            bookingQueue.addRequest(guestName + " - " + roomType);

            System.out.println("Booking request accepted!");

        } catch (InvalidBookingException e) {

            System.out.println("Booking failed: " + e.getMessage());

        } finally {
            scanner.close();
        }
    }
}