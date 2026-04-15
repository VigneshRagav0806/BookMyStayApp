import java.util.*;

// Booking Request Class
class BookingRequest {
    String guestName;
    String roomType;
    int rooms;

    public BookingRequest(String guestName, String roomType, int rooms) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.rooms = rooms;
    }
}

// Thread-safe Room Inventory
class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    // Critical Section: synchronized method
    public synchronized boolean allocateRoom(String roomType, int count) {
        if (!inventory.containsKey(roomType)) {
            System.out.println("Invalid room type: " + roomType);
            return false;
        }

        int available = inventory.get(roomType);

        if (available >= count) {
            // Simulate delay (to expose race conditions if not synchronized)
            try { Thread.sleep(100); } catch (InterruptedException e) {}

            inventory.put(roomType, available - count);
            return true;
        } else {
            return false;
        }
    }

    public synchronized void displayInventory() {
        System.out.println("\nFinal Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

// Shared Booking Queue
class BookingQueue {
    private Queue<BookingRequest> queue = new LinkedList<>();

    public synchronized void addRequest(BookingRequest request) {
        queue.add(request);
    }

    public synchronized BookingRequest getRequest() {
        return queue.poll();
    }
}

// Booking Processor (Thread)
class BookingProcessor implements Runnable {
    private BookingQueue queue;
    private RoomInventory inventory;

    public BookingProcessor(BookingQueue queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    @Override
    public void run() {
        while (true) {
            BookingRequest request;

            // Fetch request safely
            synchronized (queue) {
                request = queue.getRequest();
            }

            if (request == null) break;

            boolean success = inventory.allocateRoom(request.roomType, request.rooms);

            if (success) {
                System.out.println(Thread.currentThread().getName() +
                        " SUCCESS: " + request.guestName +
                        " booked " + request.rooms + " " + request.roomType);
            } else {
                System.out.println(Thread.currentThread().getName() +
                        " FAILED: " + request.guestName +
                        " could not book " + request.rooms + " " + request.roomType);
            }
        }
    }
}

// Main Class
public class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) throws InterruptedException {

        RoomInventory inventory = new RoomInventory();
        BookingQueue queue = new BookingQueue();

        // Simulate multiple guest requests
        queue.addRequest(new BookingRequest("Guest1", "Single", 2));
        queue.addRequest(new BookingRequest("Guest2", "Single", 2));
        queue.addRequest(new BookingRequest("Guest3", "Single", 2)); // may fail
        queue.addRequest(new BookingRequest("Guest4", "Double", 1));
        queue.addRequest(new BookingRequest("Guest5", "Suite", 1));
        queue.addRequest(new BookingRequest("Guest6", "Suite", 2)); // may fail

        // Create multiple threads
        Thread t1 = new Thread(new BookingProcessor(queue, inventory), "Thread-1");
        Thread t2 = new Thread(new BookingProcessor(queue, inventory), "Thread-2");
        Thread t3 = new Thread(new BookingProcessor(queue, inventory), "Thread-3");

        // Start threads
        t1.start();
        t2.start();
        t3.start();

        // Wait for completion
        t1.join();
        t2.join();
        t3.join();

        // Display final inventory
        inventory.displayInventory();
    }
}