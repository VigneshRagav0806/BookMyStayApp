import java.util.*;

/**
 * ============================================================
 * CLASS - Reservation
 * ============================================================
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
 * ============================================================
 * CLASS - RoomInventory
 * ============================================================
 * Maintains available room counts.
 */
class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 2);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);
    }

    public boolean isAvailable(String roomType) {
        return inventory.getOrDefault(roomType, 0) > 0;
    }

    public void allocateRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}


/**
 * ============================================================
 * CLASS - BookingRequestQueue (FIFO)
 * ============================================================
 */
class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }
}


/**
 * ============================================================
 * CLASS - RoomAllocationService
 * ============================================================
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Ensures:
 * - Unique room IDs
 * - No double booking
 * - Inventory updated immediately
 *
 * @version 6.0
 */
class RoomAllocationService {

    /** Stores all allocated room IDs */
    private Set<String> allocatedRoomIds;

    /** RoomType -> Assigned Room IDs */
    private Map<String, Set<String>> assignedRoomsByType;

    /** Constructor */
    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
    }

    /**
     * Allocates room if available
     */
    public void allocateRoom(Reservation reservation, RoomInventory inventory) {

        String roomType = reservation.getRoomType();

        if (!inventory.isAvailable(roomType)) {
            System.out.println("No " + roomType + " rooms available for " + reservation.getGuestName());
            return;
        }

        String roomId = generateRoomId(roomType);

        // store allocated room
        allocatedRoomIds.add(roomId);

        assignedRoomsByType
                .computeIfAbsent(roomType, k -> new HashSet<>())
                .add(roomId);

        // update inventory
        inventory.allocateRoom(roomType);

        System.out.println("Booking CONFIRMED for " + reservation.getGuestName()
                + " | Room Type: " + roomType
                + " | Room ID: " + roomId);
    }

    /**
     * Generates unique room ID
     */
    private String generateRoomId(String roomType) {

        String prefix = roomType.substring(0, 1).toUpperCase();
        String roomId;

        do {
            roomId = prefix + (100 + new Random().nextInt(900));
        } while (allocatedRoomIds.contains(roomId));

        return roomId;
    }
}


/**
 * ============================================================
 * MAIN CLASS - UseCase6RoomAllocation
 * ============================================================
 *
 * Demonstrates FIFO booking + safe allocation
 *
 * @version 6.0
 */
public class UseCase6RoomAllocation {

    public static void main(String[] args) {

        System.out.println("Room Allocation System");

        // Initialize
        BookingRequestQueue queue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService service = new RoomAllocationService();

        // Create requests
        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Subha", "Double"));
        queue.addRequest(new Reservation("Ram", "Suite"));
        queue.addRequest(new Reservation("Kiran", "Suite")); // should fail (only 1)

        // Process FIFO
        while (queue.hasPendingRequests()) {
            Reservation r = queue.getNextRequest();
            service.allocateRoom(r, inventory);
        }
    }
}