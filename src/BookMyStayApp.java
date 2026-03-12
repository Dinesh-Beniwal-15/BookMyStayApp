import java.util.LinkedList;
import java.util.Queue;

/**
 * ===============================================================
 * CLASS - Reservation
 * ===============================================================
 *
 * Use Case 5: Booking Request (FIFO)
 *
 * Description:
 * This class represents a booking request made by a guest.
 *
 * At this stage, a reservation only captures intent,
 * not confirmation or room allocation.
 *
 * @version 5.0
 */

class Reservation {

    /** Name of the guest making the booking */
    private String guestName;

    /** Requested room type */
    private String roomType;

    /**
     * Creates a new booking request
     *
     * @param guestName guest name
     * @param roomType requested room type
     */
    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    /** @return guest name */
    public String getGuestName() {
        return guestName;
    }

    /** @return requested room type */
    public String getRoomType() {
        return roomType;
    }
}


/**
 * ===============================================================
 * CLASS - BookingRequestQueue
 * ===============================================================
 *
 * Use Case 5: Booking Request (FIFO)
 *
 * Description:
 * This class manages booking requests using a queue
 * to ensure fair allocation.
 *
 * Requests are processed strictly in the order received.
 *
 * @version 5.0
 */

class BookingRequestQueue {

    /** Queue storing booking requests */
    private Queue<Reservation> requestQueue;

    /** Initializes empty booking queue */
    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    /**
     * Adds a booking request to the queue
     *
     * @param reservation booking request
     */
    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    /**
     * Retrieves and removes next booking request
     *
     * @return next reservation request
     */
    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    /**
     * Checks if queue has pending requests
     *
     * @return true if queue not empty
     */
    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }
}


/**
 * ===============================================================
 * MAIN CLASS - BookMyStayApp
 * ===============================================================
 *
 * Use Case 5: Booking Request Queue (First-Come-First-Served)
 *
 * Description:
 * Demonstrates how booking requests are accepted
 * and queued in a fair order.
 *
 * No room allocation or inventory updates happen here.
 *
 * @version 5.0
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        // Display application header
        System.out.println("Booking Request Queue");

        // Initialize booking queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Create booking requests
        Reservation r1 = new Reservation("Abhi", "Single");
        Reservation r2 = new Reservation("Subha", "Double");
        Reservation r3 = new Reservation("Vanmathi", "Suite");

        // Add requests to queue
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        // Process booking requests in FIFO order
        while (bookingQueue.hasPendingRequests()) {

            Reservation current = bookingQueue.getNextRequest();

            System.out.println(
                    "Processing booking for Guest: "
                            + current.getGuestName()
                            + ", Room Type: "
                            + current.getRoomType()
            );
        }
    }
}