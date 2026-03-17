import java.util.*;

/**
 * ===============================================================
 * CLASS - Reservation
 * ===============================================================
 *
 * Represents a confirmed reservation.
 *
 * @version 8.0
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
 * CLASS - BookingHistory
 * ===============================================================
 *
 * Stores confirmed reservations in order.
 *
 * @version 8.0
 */

class BookingHistory {

    /** List storing confirmed reservations */
    private List<Reservation> confirmedReservations;

    /** Initializes empty history */
    public BookingHistory() {
        confirmedReservations = new ArrayList<>();
    }

    /** Adds reservation to history */
    public void addReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
    }

    /** Returns all reservations */
    public List<Reservation> getConfirmedReservations() {
        return confirmedReservations;
    }
}


/**
 * ===============================================================
 * CLASS - BookingReportService
 * ===============================================================
 *
 * Generates reports from booking history.
 *
 * @version 8.0
 */

class BookingReportService {

    /**
     * Displays summary report
     */
    public void generateReport(BookingHistory history) {

        System.out.println("Booking History Report\n");

        List<Reservation> reservations = history.getConfirmedReservations();

        if (reservations.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        int count = 1;

        for (Reservation r : reservations) {

            System.out.println("Booking " + count++);
            System.out.println("Guest: " + r.getGuestName());
            System.out.println("Room Type: " + r.getRoomType());
            System.out.println();
        }

        System.out.println("Total Bookings: " + reservations.size());
    }
}


/**
 * ===============================================================
 * MAIN CLASS - BookMyStayApp
 * ===============================================================
 *
 * Use Case 8: Booking History & Reporting
 *
 * Demonstrates storing and reporting bookings.
 *
 * @version 8.0
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Booking History & Reporting\n");

        // Initialize history
        BookingHistory history = new BookingHistory();

        // Add confirmed bookings
        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));

        // Generate report
        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history);
    }
}