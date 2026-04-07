import java.util.HashMap;
import java.util.Map;

/**
 * Abstract Room class
 * Represents common properties of all rooms
 */
abstract class Room {
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;
    protected String roomType;

    public Room(String roomType, int beds, int squareFeet, double pricePerNight) {
        this.roomType = roomType;
        this.numberOfBeds = beds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getBeds() {
        return numberOfBeds;
    }

    public int getSquareFeet() {
        return squareFeet;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }
}

/**
 * Concrete Room Types
 */
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single", 1, 250, 1500.0);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double", 2, 400, 2500.0);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite", 3, 750, 5000.0);
    }
}

/**
 * RoomInventory class (as per your screenshot structure)
 */
class RoomInventory {

    /**
     * Stores available room count for each room type.
     */
    private Map<String, Integer> roomAvailability;

    /**
     * Constructor initializes inventory
     */
    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    /**
     * Initializes room availability
     */
    private void initializeInventory() {
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    /**
     * Returns availability map
     */
    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    /**
     * Update availability
     */
    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }

    /**
     * Get specific room count
     */
    public int getAvailableRooms(String roomType) {
        return roomAvailability.getOrDefault(roomType, 0);
    }
}

/**
 * =========================================================
 * MAIN CLASS - UseCase3InventorySetup
 * =========================================================
 *
 * Use Case 3: Centralized Room Inventory Management
 */
public class UseCase3InventorySetup {

    public static void main(String[] args) {

        // Create Room objects
        Room single = new SingleRoom();
        Room dbl = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Create Inventory
        RoomInventory inventory = new RoomInventory();

        // Display Output
        System.out.println("Hotel Room Inventory Status\n");

        displayRoom(single, inventory);
        displayRoom(dbl, inventory);
        displayRoom(suite, inventory);
    }

    /**
     * Display method (keeps main clean)
     */
    private static void displayRoom(Room room, RoomInventory inventory) {
        System.out.println(room.getRoomType() + " Room:");
        System.out.println("Beds: " + room.getBeds());
        System.out.println("Size: " + room.getSquareFeet() + " sqft");
        System.out.println("Price per night: " + room.getPricePerNight());
        System.out.println("Available Rooms: " + inventory.getAvailableRooms(room.getRoomType()));
        System.out.println();
    }
}