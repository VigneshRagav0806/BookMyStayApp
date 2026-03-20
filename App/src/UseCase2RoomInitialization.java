abstract class Room
{
    protected String roomType;
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricepernight;

    public Room(String roomType,int numberOfBeds, int squareFeet, double pricepernight)
    {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.squareFeet=squareFeet;
        this.pricepernight=pricepernight;
    }
    public String getRoomType()
    {
        return roomType;
    }
    public int getNumberOfBeds()
    {
        return numberOfBeds;
    }
    public int getSquareFeet()
    {
        return squareFeet;
    }
    public double getPricepernight()
    {
        return pricepernight;
    }
    public void displayRoomDetails()
    {
        System.out.println(roomType+":");
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + squareFeet);
        System.out.println("Price per Night: " + pricepernight);
    }
}
class SingleRoom extends Room
{
    public SingleRoom()
    {
        super("Single Room",1, 250, 1500.0);
    }
}
class DoubleRoom extends Room
{
    public DoubleRoom()
    {
        super("DoubleRoom",2,400,2500.0);
    }
}
class SuiteRoom extends Room
{
    public SuiteRoom()
    {
        super("SuiteRoom",3,750,3500.0);
    }
}
public class UseCase2RoomInitialization
{
    public static void main(String[] args)
    {
        int singleRoomAvailability = 5;
        int doubleRoomAvailability = 3;
        int suiteRoomAvailability = 2;

        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        System.out.println("Hotel Room Initialization");
        singleRoom.displayRoomDetails();
        System.out.println("Available: " + singleRoomAvailability);
        System.out.println("\n");
        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + doubleRoomAvailability);
        System.out.println("\n");
        System.out.println("Suite Room Availability: " + suiteRoomAvailability);
        suiteRoom.displayRoomDetails();
        System.out.println("Available: " + suiteRoomAvailability);
        System.out.println("\n");
        System.out.println("Application Terminated");
    }
}