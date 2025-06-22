public class CarBooking {
    private String bookingID;    // Unique ID for the booking
    private String custName;     // Name of the customer who made the booking
    private String carModel;     // Car model being booked
    private String pickupDate;   // Date when the car is picked up
    private String returnDate;   // Date when the car is returned
    private int time;            // Time for pickup/return (in hours)
    private double price;        // Price of the booking
    private int day;             // Number of days the car is booked
    private String status;       // Current status of the booking (e.g., "Confirmed", "Cancelled")
    public CarBooking() {}
    public CarBooking(String bID, String c, String cM, String pD, String rD, int T, double p, int d, String s) {
        this.bookingID = bID;    // Set booking ID
        this.custName = c;       // Set customer name
        this.carModel = cM;      // Set car model
        this.pickupDate = pD;    // Set pickup date
        this.returnDate = rD;    // Set return date
        this.time = T;           // Set pickup/return time
        this.price = p;          // Set price of booking
        this.day = d;            // Set number of days booked
        this.status = s;         // Set booking status
    }
    public void setCarBooking(String bID, String c, String cM, String pD, String rD, int T, double p, int d, String s) {
        this.bookingID = bID;
        this.custName = c;
        this.carModel = cM;
        this.pickupDate = pD;
        this.returnDate = rD;
        this.time = T;
        this.price = p;
        this.day = d;
        this.status = s;
    }
    public void setStatus(String s) {
        status = s;
    }
    public String getBookingID() {
        return bookingID;
    }
    public String getCustName() {
        return custName;
    }
    public String getCarModel() {
        return carModel;
    }
    public String getPickupDate() {
        return pickupDate;
    }
    public String getReturnDate() {
        return returnDate;
    }
    public int getTime() {
        return time;
    }
    public double getPrice() {
        return price;
    }
    public int getDay() {
        return day;
    }
    public String getStatus() {
        return status;
    }
    public String toString() {
        return "Booking ID: " + bookingID +
               "\nCustomer Name: " + custName +
               "\nCar Model: " + carModel +
               "\nPickup Date: " + pickupDate +
               "\nPickup and Return Time: " + time +
               "\nReturn Date: " + returnDate +
               "\nPrice: RM" + price +
               "\nDays Booked: " + day +
               "\nStatus: " + status + "\n";
    }
}
