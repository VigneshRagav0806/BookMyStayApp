import java.util.HashMap;
import java.util.Map;

/**
 * ================================================================
 * ABSTRACT CLASS - Room
 * ================================================================
 */
abstract class Room {
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    public void displayRoomDetails() {
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Area: " + squareFeet + " sq.ft");
        System.out.println("Price per night: ₹" + pricePerNight);
    }
}

/**
 * ================================================================
 * ROOM TYPES
 * ================================================================
 */
class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 200, 1500);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 350, 2500);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super(3, 600, 5000);
    }
}

/**
 * ================================================================
 * CLASS - RoomInventory
 * ================================================================
 */
class RoomInventory {
    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();

        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability; // Read-only usage
    }
}

/**
 * ================================================================
 * CLASS - RoomSearchService
 * ================================================================
 */
class RoomSearchService {

    public void searchAvailableRooms(
            RoomInventory inventory,
            Room singleRoom,
            Room doubleRoom,
            Room suiteRoom) {

        Map<String, Integer> availability = inventory.getRoomAvailability();

        System.out.println("====== AVAILABLE ROOMS ======");

        if (availability.get("Single") > 0) {
            System.out.println("\nSingle Room Available:");
            singleRoom.displayRoomDetails();
            System.out.println("Available Units: " + availability.get("Single"));
        }

        if (availability.get("Double") > 0) {
            System.out.println("\nDouble Room Available:");
            doubleRoom.displayRoomDetails();
            System.out.println("Available Units: " + availability.get("Double"));
        }

        if (availability.get("Suite") > 0) {
            System.out.println("\nSuite Room Available:");
            suiteRoom.displayRoomDetails();
            System.out.println("Available Units: " + availability.get("Suite"));
        }

        System.out.println("\n=============================");
    }
}

/**
 * ================================================================
 * MAIN CLASS - UseCase4RoomSearch
 * ================================================================
 */
public class UseCase3RoomSearch {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        RoomSearchService service = new RoomSearchService();

        service.searchAvailableRooms(
                inventory,
                singleRoom,
                doubleRoom,
                suiteRoom
        );
    }
}

