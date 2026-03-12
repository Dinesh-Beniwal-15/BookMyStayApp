import java.util.*;

/**
 * ===============================================================
 * CLASS - Reservation
 * ===============================================================
 *
 * Represents a booking request made by a guest.
 *
 * @version 6.0
 */

class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}


/**
 * ===============================================================
 * CLASS - BookingRequestQueue
 * ===============================================================
 *
 * Manages booking requests using FIFO queue.
 *
 * @version 6.0
 */

class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }
}


/**
 * ===============================================================
 * CLASS - RoomInventory
 * ===============================================================
 *
 * Maintains room availability.
 *
 * @version 6.0
 */

class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() {
        roomAvailability.put("Single", 2);
        roomAvailability.put("Double", 1);
        roomAvailability.put("Suite", 1);
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
 * CLASS - RoomAllocationService
 * ===============================================================
 *
 * Confirms booking requests and assigns rooms.
 *
 * Ensures:
 * - Unique room IDs
 * - Inventory update
 * - No double booking
 *
 * @version 6.0
 */

class RoomAllocationService {

    /** Stores all allocated room IDs */
    private Set<String> allocatedRoomIds;

    /** Stores assigned rooms grouped by type */
    private Map<String, Set<String>> assignedRoomsByType;

    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
    }

    public void allocateRoom(Reservation reservation, RoomInventory inventory) {

        String roomType = reservation.getRoomType();
        Map<String, Integer> availability = inventory.getRoomAvailability();

        int availableRooms = availability.getOrDefault(roomType, 0);

        if (availableRooms <= 0) {
            System.out.println("No rooms available for " + reservation.getGuestName());
            return;
        }

        String roomId = generateRoomId(roomType);

        allocatedRoomIds.add(roomId);

        assignedRoomsByType
                .computeIfAbsent(roomType, k -> new HashSet<>())
                .add(roomId);

        inventory.updateAvailability(roomType, availableRooms - 1);

        System.out.println(
                "Booking confirmed for Guest: "
                        + reservation.getGuestName()
                        + ", Room ID: "
                        + roomId);
    }

    private String generateRoomId(String roomType) {

        int nextId = 1;

        if (assignedRoomsByType.containsKey(roomType)) {
            nextId = assignedRoomsByType.get(roomType).size() + 1;
        }

        return roomType + "-" + nextId;
    }
}


/**
 * ===============================================================
 * MAIN CLASS - BookMyStayApp
 * ===============================================================
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Demonstrates how booking requests are confirmed
 * and rooms are allocated safely.
 *
 * @version 6.0
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Room Allocation Processing");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Subha", "Single"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Suite"));

        RoomInventory inventory = new RoomInventory();

        RoomAllocationService allocationService = new RoomAllocationService();

        while (bookingQueue.hasPendingRequests()) {

            Reservation request = bookingQueue.getNextRequest();

            allocationService.allocateRoom(request, inventory);
        }
    }
}