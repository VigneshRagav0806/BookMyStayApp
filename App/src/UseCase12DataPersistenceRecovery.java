import java.io.*;
import java.util.*;

// Booking class (Serializable)
class Booking implements Serializable {
    String bookingId;
    String roomType;
    int rooms;

    public Booking(String bookingId, String roomType, int rooms) {
        this.bookingId = bookingId;
        this.roomType = roomType;
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return bookingId + " | " + roomType + " | " + rooms;
    }
}

// Wrapper class for persistence
class SystemState implements Serializable {
    Map<String, Integer> inventory;
    Map<String, Booking> bookings;

    public SystemState(Map<String, Integer> inventory, Map<String, Booking> bookings) {
        this.inventory = inventory;
        this.bookings = bookings;
    }
}

// Inventory Manager
class RoomInventory implements Serializable {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }

    public boolean allocateRoom(String type, int count) {
        if (!inventory.containsKey(type)) return false;

        int available = inventory.get(type);
        if (available < count) return false;

        inventory.put(type, available - count);
        return true;
    }

    public void display() {
        System.out.println("\nInventory:");
        for (Map.Entry<String, Integer> e : inventory.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }
}

// Persistence Service
class PersistenceService {
    private static final String FILE_NAME = "system_state.ser";

    public static void save(SystemState state) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(state);
            System.out.println("System state saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving system state.");
        }
    }

    public static SystemState load() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            System.out.println("System state restored successfully.");
            return (SystemState) ois.readObject();

        } catch (FileNotFoundException e) {
            System.out.println("No previous state found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Corrupted data. Starting with safe defaults.");
        }
        return null;
    }
}

// Booking Service
class BookingService {
    private RoomInventory inventory;
    private Map<String, Booking> bookings;
    private int counter = 1;

    public BookingService(RoomInventory inventory, Map<String, Booking> bookings) {
        this.inventory = inventory;
        this.bookings = bookings;
        this.counter = bookings.size() + 1;
    }

    public void createBooking(String type, int count) {
        if (inventory.allocateRoom(type, count)) {
            String id = "B" + counter++;
            Booking b = new Booking(id, type, count);
            bookings.put(id, b);
            System.out.println("Booking successful: " + id);
        } else {
            System.out.println("Booking failed.");
        }
    }

    public void showBookings() {
        System.out.println("\nBookings:");
        for (Booking b : bookings.values()) {
            System.out.println(b);
        }
    }

    public Map<String, Booking> getBookings() {
        return bookings;
    }
}

// Main Class
public class UseCase12DataPersistenceRecovery {
    public static void main(String[] args) {

        // Load previous state
        SystemState state = PersistenceService.load();

        RoomInventory inventory;
        Map<String, Booking> bookings;

        if (state != null) {
            inventory = new RoomInventory();
            inventory.setInventory(state.inventory);
            bookings = state.bookings;
        } else {
            inventory = new RoomInventory();
            bookings = new HashMap<>();
        }

        BookingService service = new BookingService(inventory, bookings);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Book My Stay ===");
            System.out.println("1. Book Room");
            System.out.println("2. View Inventory");
            System.out.println("3. View Bookings");
            System.out.println("4. Save & Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Room Type (Single/Double/Suite): ");
                    String type = sc.nextLine();

                    System.out.print("Number of rooms: ");
                    int count = sc.nextInt();
                    sc.nextLine();

                    service.createBooking(type, count);
                    break;

                case 2:
                    inventory.display();
                    break;

                case 3:
                    service.showBookings();
                    break;

                case 4:
                    // Save state before exit
                    SystemState newState = new SystemState(
                            inventory.getInventory(),
                            service.getBookings()
                    );

                    PersistenceService.save(newState);
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}