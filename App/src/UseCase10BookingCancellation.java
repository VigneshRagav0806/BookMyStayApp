import java.util.*;

// Custom Exception for booking errors
class BookingException extends Exception {
    public BookingException(String message) {
        super(message);
    }
}

// Booking class
class Booking {
    String bookingId;
    String roomType;
    int roomsBooked;
    boolean isCancelled;

    public Booking(String bookingId, String roomType, int roomsBooked) {
        this.bookingId = bookingId;
        this.roomType = roomType;
        this.roomsBooked = roomsBooked;
        this.isCancelled = false;
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

    public void allocateRoom(String type, int count) throws BookingException {
        if (!isValidRoomType(type)) {
            throw new BookingException("Invalid room type: " + type);
        }

        if (count <= 0) {
            throw new BookingException("Invalid room count.");
        }

        int available = getAvailableRooms(type);

        if (available < count) {
            throw new BookingException("Not enough rooms available.");
        }

        inventory.put(type, available - count);
    }

    public void restoreRoom(String type, int count) {
        int available = getAvailableRooms(type);
        inventory.put(type, available + count);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

// Booking Service with rollback using Stack
class BookingService {
    private RoomInventory inventory;
    private Map<String, Booking> bookings;
    private Stack<String> rollbackStack;
    private int bookingCounter = 1;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        bookings = new HashMap<>();
        rollbackStack = new Stack<>();
    }

    // Create Booking
    public void createBooking(String roomType, int count) {
        try {
            inventory.allocateRoom(roomType, count);

            String bookingId = "B" + bookingCounter++;
            Booking booking = new Booking(bookingId, roomType, count);
            bookings.put(bookingId, booking);

            System.out.println("Booking successful! Booking ID: " + bookingId);

        } catch (BookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }

    // Cancel Booking with rollback
    public void cancelBooking(String bookingId) {
        try {
            // Validate existence
            if (!bookings.containsKey(bookingId)) {
                throw new BookingException("Booking ID does not exist.");
            }

            Booking booking = bookings.get(bookingId);

            // Prevent duplicate cancellation
            if (booking.isCancelled) {
                throw new BookingException("Booking already cancelled.");
            }

            // Step 1: Push to rollback stack
            rollbackStack.push(bookingId);

            // Step 2: Restore inventory
            inventory.restoreRoom(booking.roomType, booking.roomsBooked);

            // Step 3: Mark as cancelled
            booking.isCancelled = true;

            System.out.println("Cancellation successful for Booking ID: " + bookingId);

        } catch (BookingException e) {
            System.out.println("Cancellation failed: " + e.getMessage());
        }
    }

    public void showBookings() {
        System.out.println("\nBooking History:");
        for (Booking b : bookings.values()) {
            System.out.println(
                    b.bookingId + " | " + b.roomType + " | " + b.roomsBooked +
                            " | Status: " + (b.isCancelled ? "Cancelled" : "Active")
            );
        }
    }

    public void showRollbackStack() {
        System.out.println("\nRollback Stack (Recent Cancellations): " + rollbackStack);
    }
}

// Main Class
public class UseCase10BookingCancellation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService(inventory);

        while (true) {
            System.out.println("\n=== Book My Stay ===");
            System.out.println("1. Book Room");
            System.out.println("2. Cancel Booking");
            System.out.println("3. View Inventory");
            System.out.println("4. View Bookings");
            System.out.println("5. View Rollback Stack");
            System.out.println("6. Exit");

            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Room Type (Single/Double/Suite): ");
                    String type = sc.nextLine();

                    System.out.print("Enter number of rooms: ");
                    int count = sc.nextInt();
                    sc.nextLine();

                    service.createBooking(type, count);
                    break;

                case 2:
                    System.out.print("Enter Booking ID to cancel: ");
                    String id = sc.nextLine();
                    service.cancelBooking(id);
                    break;

                case 3:
                    inventory.displayInventory();
                    break;

                case 4:
                    service.showBookings();
                    break;

                case 5:
                    service.showRollbackStack();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}