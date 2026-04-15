import java.util.*;

// Custom Exception for invalid booking scenarios
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Room Inventory Manager
class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public boolean isValidRoomType(String type) {
        return inventory.containsKey(type);
    }

    public int getAvailableRooms(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void bookRoom(String type, int count) throws InvalidBookingException {
        // Guard condition: room type must exist
        if (!isValidRoomType(type)) {
            throw new InvalidBookingException("Invalid room type: " + type);
        }

        // Guard condition: count must be positive
        if (count <= 0) {
            throw new InvalidBookingException("Booking count must be greater than 0");
        }

        int available = getAvailableRooms(type);

        // Guard condition: sufficient rooms must be available
        if (available < count) {
            throw new InvalidBookingException(
                    "Not enough rooms available. Requested: " + count + ", Available: " + available
            );
        }

        // Update inventory safely
        inventory.put(type, available - count);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Room Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " Rooms: " + entry.getValue());
        }
    }
}

// Booking Service with validation
class BookingService {
    private RoomInventory inventory;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void processBooking(String roomType, int count) {
        try {
            inventory.bookRoom(roomType, count);
            System.out.println("Booking successful for " + count + " " + roomType + " room(s).");
        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }
}

// Main Class
public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService(inventory);

        System.out.println("=== Welcome to Book My Stay ===");

        while (true) {
            inventory.displayInventory();

            System.out.print("\nEnter Room Type (Single/Double/Suite or 'exit'): ");
            String roomType = sc.nextLine();

            if (roomType.equalsIgnoreCase("exit")) {
                System.out.println("Exiting system...");
                break;
            }

            System.out.print("Enter number of rooms: ");
            int count;

            try {
                count = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: Please enter a valid number.");
                continue; // Fail-fast
            }

            // Process booking with validation
            service.processBooking(roomType, count);
        }

        sc.close();
    }
}