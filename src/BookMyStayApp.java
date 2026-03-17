import java.util.*;

/**
 * ===============================================================
 * CLASS - AddOnService
 * ===============================================================
 *
 * Represents an optional service.
 *
 * @version 7.0
 */

class AddOnService {

    /** Name of the service */
    private String serviceName;

    /** Cost of the service */
    private double cost;

    /**
     * Creates a new add-on service
     */
    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}


/**
 * ===============================================================
 * CLASS - AddOnServiceManager
 * ===============================================================
 *
 * Manages services for reservations.
 *
 * @version 7.0
 */

class AddOnServiceManager {

    /**
     * Key   -> Reservation ID
     * Value -> List of services
     */
    private Map<String, List<AddOnService>> servicesByReservation;

    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    /**
     * Adds service to a reservation
     */
    public void addService(String reservationId, AddOnService service) {

        servicesByReservation
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);
    }

    /**
     * Calculates total cost of services
     */
    public double calculateTotalServiceCost(String reservationId) {

        List<AddOnService> services = servicesByReservation.get(reservationId);

        if (services == null) return 0;

        double total = 0;

        for (AddOnService service : services) {
            total += service.getCost();
        }

        return total;
    }

    /**
     * Displays services for a reservation
     */
    public void displayServices(String reservationId) {

        List<AddOnService> services = servicesByReservation.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        System.out.println("Selected Services:");

        for (AddOnService service : services) {
            System.out.println("- " + service.getServiceName()
                    + " (" + service.getCost() + ")");
        }
    }
}


/**
 * ===============================================================
 * MAIN CLASS - BookMyStayApp
 * ===============================================================
 *
 * Use Case 7: Add-On Service Selection
 *
 * Demonstrates attaching services to a reservation.
 *
 * @version 7.0
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Add-On Service Selection\n");

        String reservationId = "RES-101";

        AddOnServiceManager manager = new AddOnServiceManager();

        // Create services
        AddOnService breakfast = new AddOnService("Breakfast", 500);
        AddOnService spa = new AddOnService("Spa", 1200);
        AddOnService pickup = new AddOnService("Airport Pickup", 800);

        // Add services to reservation
        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, spa);
        manager.addService(reservationId, pickup);

        // Display services
        manager.displayServices(reservationId);

        // Calculate total cost
        double totalCost = manager.calculateTotalServiceCost(reservationId);

        System.out.println("\nTotal Add-On Cost: " + totalCost);
    }
}