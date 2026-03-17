import java.util.*;

/**
 * ===============================================================
 * CLASS - Reservation
 * ===============================================================
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
 */

class BookingRequestQueue {

    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) {
        queue.offer(r);
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}


/**
 * ===============================================================
 * CLASS - RoomInventory
 * ===============================================================
 */

class RoomInventory {

    private Map<String, Integer> availability = new HashMap<>();

    public RoomInventory() {
        availability.put("Single", 2);
        availability.put("Double", 2);
        availability.put("Suite", 1);
    }

    public Map<String, Integer> getAvailability() {
        return availability;
    }

    public void update(String type, int count) {
        availability.put(type, count);
    }
}


/**
 * ===============================================================
 * CLASS - RoomAllocationService
 * ===============================================================
 */

class RoomAllocationService {

    private Map<String, Integer> counters = new HashMap<>();

    public void allocateRoom(Reservation r, RoomInventory inventory) {

        String type = r.getRoomType();
        Map<String, Integer> availability = inventory.getAvailability();

        int available = availability.getOrDefault(type, 0);

        if (available <= 0) {
            System.out.println("No rooms available for " + r.getGuestName());
            return;
        }

        int id = counters.getOrDefault(type, 0) + 1;
        counters.put(type, id);

        inventory.update(type, available - 1);

        System.out.println("Booking confirmed for Guest: "
                + r.getGuestName()
                + ", Room ID: " + type + "-" + id);
    }
}


/**
 * ===============================================================
 * CLASS - ConcurrentBookingProcessor
 * ===============================================================
 */

class ConcurrentBookingProcessor implements Runnable {

    private BookingRequestQueue queue;
    private RoomInventory inventory;
    private RoomAllocationService service;

    public ConcurrentBookingProcessor(
            BookingRequestQueue queue,
            RoomInventory inventory,
            RoomAllocationService service) {

        this.queue = queue;
        this.inventory = inventory;
        this.service = service;
    }

    @Override
    public void run() {

        while (true) {

            Reservation reservation;

            // Critical Section 1 → Queue access
            synchronized (queue) {

                if (queue.isEmpty()) return;

                reservation = queue.getNextRequest();
            }

            // Critical Section 2 → Inventory + allocation
            synchronized (inventory) {
                service.allocateRoom(reservation, inventory);
            }
        }
    }
}


/**
 * ===============================================================
 * MAIN CLASS - BookMyStayApp
 * ===============================================================
 *
 * Use Case 11: Concurrent Booking Simulation
 *
 * @version 11.0
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Concurrent Booking Simulation\n");

        BookingRequestQueue queue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService service = new RoomAllocationService();

        // Add booking requests
        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Vanmathi", "Double"));
        queue.addRequest(new Reservation("Kural", "Suite"));
        queue.addRequest(new Reservation("Subha", "Single"));

        // Create threads
        Thread t1 = new Thread(
                new ConcurrentBookingProcessor(queue, inventory, service));

        Thread t2 = new Thread(
                new ConcurrentBookingProcessor(queue, inventory, service));

        // Start threads
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        // Final inventory
        System.out.println("\nRemaining Inventory:");

        Map<String, Integer> remaining = inventory.getAvailability();

        System.out.println("Single: " + remaining.get("Single"));
        System.out.println("Double: " + remaining.get("Double"));
        System.out.println("Suite: " + remaining.get("Suite"));
    }
}