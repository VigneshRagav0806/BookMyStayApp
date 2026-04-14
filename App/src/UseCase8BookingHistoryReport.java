import java.util.*;

// Reservation class (represents a confirmed booking)
class Reservation {
    private String customerName;
    private String roomType;
    private int nights;
    private double pricePerNight;

    public Reservation(String customerName, String roomType, int nights, double pricePerNight) {
        this.customerName = customerName;
        this.roomType = roomType;
        this.nights = nights;
        this.pricePerNight = pricePerNight;
    }

    public double getTotalCost() {
        return nights * pricePerNight;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNights() {
        return nights;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    @Override
    public String toString() {
        return "Customer: " + customerName +
                ", Room: " + roomType +
                ", Nights: " + nights +
                ", Total Cost: ₹" + getTotalCost();
    }
}

// Booking History (stores confirmed bookings)
class BookingHistory {
    private List<Reservation> reservations;

    public BookingHistory() {
        reservations = new ArrayList<>();
    }

    // Add confirmed booking
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    // Retrieve all bookings
    public List<Reservation> getAllReservations() {
        return reservations;
    }
}

// Report Service (generates reports)
class BookingReportService {

    // Generate summary report
    public void generateSummary(List<Reservation> reservations) {
        System.out.println("\n===== BOOKING SUMMARY REPORT =====");

        int totalBookings = reservations.size();
        double totalRevenue = 0;

        for (Reservation r : reservations) {
            totalRevenue += r.getTotalCost();
        }

        System.out.println("Total Bookings: " + totalBookings);
        System.out.println("Total Revenue: ₹" + totalRevenue);
    }

    // Detailed report
    public void generateDetailedReport(List<Reservation> reservations) {
        System.out.println("\n===== DETAILED BOOKING REPORT =====");

        for (Reservation r : reservations) {
            System.out.println(r);
        }
    }
}

// Main class
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // Simulating confirmed bookings
        Reservation r1 = new Reservation("Vicky", "Deluxe", 2, 2500);
        Reservation r2 = new Reservation("Arjun", "Suite", 3, 4000);
        Reservation r3 = new Reservation("Kiran", "Standard", 1, 1500);

        // Adding to history (in order)
        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        // Admin retrieves history
        List<Reservation> allReservations = history.getAllReservations();

        // Generate reports
        reportService.generateDetailedReport(allReservations);
        reportService.generateSummary(allReservations);
    }
}