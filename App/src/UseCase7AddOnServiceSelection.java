import java.util.*;

// Represents an Add-On Service
class AddOnService {
    private String serviceName;
    private double cost;

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

    @Override
    public String toString() {
        return serviceName + " (₹" + cost + ")";
    }
}

// Manages Add-On Services for Reservations
class AddOnServiceManager {

    // Map: Reservation ID -> List of Services
    private Map<String, List<AddOnService>> reservationServicesMap = new HashMap<>();

    // Add service to reservation
    public void addService(String reservationId, AddOnService service) {
        reservationServicesMap
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);
    }

    // Get services for a reservation
    public List<AddOnService> getServices(String reservationId) {
        return reservationServicesMap.getOrDefault(reservationId, new ArrayList<>());
    }

    // Calculate total additional cost
    public double calculateTotalCost(String reservationId) {
        List<AddOnService> services = getServices(reservationId);
        double total = 0;

        for (AddOnService service : services) {
            total += service.getCost();
        }

        return total;
    }

    // Display services
    public void displayServices(String reservationId) {
        List<AddOnService> services = getServices(reservationId);

        if (services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        System.out.println("Selected Add-On Services:");
        for (AddOnService service : services) {
            System.out.println("- " + service);
        }

        System.out.println("Total Add-On Cost: ₹" + calculateTotalCost(reservationId));
    }
}

// Main Class
public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        AddOnServiceManager manager = new AddOnServiceManager();

        System.out.print("Enter Reservation ID: ");
        String reservationId = scanner.nextLine();

        while (true) {
            System.out.println("\nChoose Add-On Service:");
            System.out.println("1. Breakfast (₹500)");
            System.out.println("2. Airport Pickup (₹1000)");
            System.out.println("3. Extra Bed (₹800)");
            System.out.println("4. Finish Selection");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    manager.addService(reservationId, new AddOnService("Breakfast", 500));
                    break;
                case 2:
                    manager.addService(reservationId, new AddOnService("Airport Pickup", 1000));
                    break;
                case 3:
                    manager.addService(reservationId, new AddOnService("Extra Bed", 800));
                    break;
                case 4:
                    System.out.println("\nFinal Summary for Reservation ID: " + reservationId);
                    manager.displayServices(reservationId);
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}