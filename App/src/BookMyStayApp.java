public class BookMyStayApp
{
    public static abstract class Room
    {
        protected int numberOfBeds;
        protected int squareFeet;
        protected double pricePerNight;

        public Room(int numberOfBeds, int squareFeet, double pricePerNight)
        {
            this.numberOfBeds=numberOfBeds;
            this.squareFeet=squareFeet;
            this.pricePerNight=pricePerNight;
        }
        public void displayRoomDetails()
        {
            System.out.println("Beds : "+numberOfBeds);
            System.out.println("Size : "+squareFeet);
            System.out.println("Price Per Night : "+pricePerNight);

        }
    }
    public static class SingleRoom extends Room
    {
        public SingleRoom()
        {
            super(1,250,1500.0);
        }
    }
    public static class DoubleRoom extends Room
    {
        public DoubleRoom()
        {
            super(2,400,2500.0);
        }
    }
    public static class SuiteRoom extends Room
    {
        public SuiteRoom()
        {
            super(3,750,5000.0);
        }
    }
    public static void main(String[] args)
    {
        int singleRoom = 5, doubleRoom = 3, suiteRoom = 2;
        Room singleroom = new SingleRoom();
        Room doubleroom = new DoubleRoom();
        Room suiteroom = new SuiteRoom();
        System.out.println("Hotel Room Initialization");
        System.out.println("Single Room:");
        singleroom.displayRoomDetails();
        System.out.println("Available:"+singleRoom);

        System.out.println("Double Room:");
        doubleroom.displayRoomDetails();
        System.out.println("Available:"+doubleRoom);

        System.out.println("Suite Room:");
        suiteroom.displayRoomDetails();
        System.out.println("Available:"+suiteRoom);
    }
}
