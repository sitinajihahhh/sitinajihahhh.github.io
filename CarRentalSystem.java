//CarBooking.txt
B01;Yana;Toyota Vellfire;2;28-12-2024;29-12-2024;2300;860.00;Confirmed
B02;Siti;Nissan Almera;1;01-12-2024;02-12-2024;1200;90.00;Completed
B03;Nurhan;Toyota Hilux;1;25-12-2024;25-12-2024;1930;170.00;Confirmed
B04;Kira;Honda Accord;1;01-01-2024;01-01-2024;2000;143.00;Cancelled
B05;Jihan;Toyota Camry;1;27-01-2024;28-01-2024;1000;177.00;Completed
B06;Ali;Nissan Almera;1;10-02-2024;10-02-2024;1700;90.00;Confirmed
B07;Farah;Toyota Vellfire;2;15-03-2024;17-03-2024;0800;860.00;Completed
B08;Sarah;Toyota Hilux;5;20-04-2024;25-04-2024;0600;850.00;Confirmed
B09;Ahmad;Honda Accord;6;05-05-2024;11-05-2024;2000;858.00;Cancelled
B10;Azura;Toyota Camry;1;12-06-2024;13-06-2024;0500;177.00;Confirmed
B11;Hana;Nissan Almera;1;25-07-2024;25-07-2024;1400;90.00;Completed
B12;Rizwan;Toyota Vellfire;2;30-08-2024;31-08-2024;2200;860.00;Confirmed
B13;Faiz;Toyota Hilux;1;05-09-2024;05-09-2024;1600;170.00;Confirmed
B14;Shila;Honda Accord;4;10-10-2024;14-10-2024;1400;572.00;Completed
B15;Liyana;Toyota Camry;3;20-11-2024;23-11-2024;0600;531.00;Confirmed
B16;Adam;Nissan Almera;1;01-02-2024;02-02-2024;1100;90.00;Completed
B17;Aina;Toyota Hilux;2;14-03-2024;15-03-2024;1800;340.00;Confirmed
B18;Hafiz;Honda Accord;5;10-06-2024;15-06-2024;2000;715.00;Cancelled
B19;Maria;Toyota Vellfire;7;20-07-2024;26-07-2024;1500;3010.00;Completed
B20;Sofea;Toyota Camry;2;15-08-2024;16-08-2024;0900;354.00;Confirmed
//CarBookingClass
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
//LinkedList
public class LinkedList {
    protected Node first; // Reference to the first node (or head of the list)
    private Node current; // Reference to the current node (used for traversal)
    private Node last; // Reference to the last node (or tail of the list)
    public LinkedList() {
        first = null; // No nodes in the list, so first is null
        current = null; // No traversal, so current is null
        last = null; // No nodes in the list, so last is null
    }
    public boolean isEmpty() {
        if (first == null) // If the first node is null, the list is empty
            return true;
        else
            return false;
        }
    public void insertAtFront(CarBooking insertItem) {
        Node newNode = new Node(insertItem); // Create a new node containing the data
        if (isEmpty()) { 
            // If the list is empty, the new node becomes both first and last
            first = newNode;
            last = newNode;
        } else {
            // Otherwise, link the new node to the current first node and update first
            newNode.next = first;
            first = newNode;
        }
    }
    public void insertAtBack(CarBooking insertItem) {
        Node newNode = new Node(insertItem); // Create a new node containing the data
        if (isEmpty()) {
            // If the list is empty, the new node becomes both first and last
            first = newNode;
            last = newNode;
        } else {
            // Otherwise, link the current last node to the new node and update last
            last.next = newNode;
            last = newNode;
        }
    }
    public CarBooking removeFromFront() {
        if (isEmpty()) // If the list is empty, return null
            return null;
        CarBooking r = first.data; // Retrieve the data from the first node
        if (first == last) { 
            // If there's only one node, set both first and last to null
            first = null;
            last = null;
        } else {
            // Otherwise, update first to point to the next node
            first = first.next;
        }
        return r; // Return the removed data
    }
    public Object removeFromBack() {
        if (isEmpty()) // If the list is empty, return null
            return null;
        
        Object r = last.data; // Retrieve the data from the last node
        
        if (first == last) {
            // If there's only one node, set both first and last to null
            first = null;
            last = null;
        } else {
            // Otherwise, traverse the list to find the second-to-last node
            current = first;
            while (current.next != last)
                current = current.next;
            
            // Update last to the second-to-last node and unlink the last node
            last = current;
            last.next = null;
        }
        return r; // Return the removed data
    }
    public CarBooking getFirst() {
        if (isEmpty()) // If the list is empty, return null
            return null;
        else {
            current = first; // Set current to the first node
            return current.data; // Return the data of the first node
        }
    }
    public Object getNext() {
        if (current == last) // If current is at the last node, return null
            return null;
        else {
            current = current.next; // Move to the next node
            return current.data; // Return the data of the current node
        }
    }
    public Node getLastNode() {
        if (isEmpty()) { // If the list is empty, return null
            return null;
        } else {
            Node temp = first; // Start from the first node
            while (temp.next != null) {
                temp = temp.next; // Traverse until the last node is reached
            }
            return temp; // Return the last node
        }
    }
    public void traverse() {
        Node current = first; // Start from the first node
        if (current == null) {
            System.out.println("The list is empty."); // If the list is empty, print a message
            return;
        }
        while (current != null) { 
            // Traverse the list and print the data of each node
            System.out.println(current.data);
            current = current.next;
        }
    }
    public Node getFirstNode() {
        return first; // Return the reference to the first node
    }
    public void displayAll() {
    if (isEmpty()) {
            // If the list is empty, print a message
            System.out.println("The list is empty.");
        } else {
            // Otherwise, traverse and display the data of each node
            traverse();
        }
    }
}
//QUeue
public class Queue extends LinkedList
{
    public Queue()
    {
       super(); 
    }
    public void enqueue(CarBooking elem)
    {
        super.insertAtBack(elem);
    }
    public CarBooking dequeue()
    {
        return super.removeFromFront();
    }
    public CarBooking getFront()
    {
        return super.getFirst();
    }
    public boolean isEmpty()
    {
        return super.isEmpty();
    }
}
//Node
public class Node {
    public CarBooking data; 
    public Node next; 
    public Node(CarBooking d) {
        this.data = d;  // Assign the provided CarBooking object to the 'data' field.
        this.next = null; // Initialize the 'next' field to null.
    }
}
//QueueApp
import java.util.*;
import java.io.*;
import java.util.StringTokenizer;
public class QueueApp {
    public static void main(String args[]) {
        try {
            Queue theQueue = new Queue();
            CarBooking cb;
            BufferedReader br = new BufferedReader(new FileReader("CarBooking.txt"));
            Scanner sc = new Scanner(System.in);
            String record;
            while ((record = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(record, ";");
                String bID = st.nextToken();                // Booking ID
                String c = st.nextToken();                  // Customer Name
                String cM = st.nextToken();                 // Car Model
                int d = Integer.parseInt(st.nextToken());   // Days Booked
                String pD = st.nextToken();                 // Pickup Date
                String rD = st.nextToken();                 // Return Date
                int T = Integer.parseInt(st.nextToken());   // Time
                double p = Double.parseDouble(st.nextToken()); // Price
                String s = st.nextToken();                  // Status
                cb = new CarBooking(bID, c, cM, pD, rD, T, p, d, s);
                theQueue.enqueue(cb); // Add booking to the queue
            }
            boolean proceed = true; // Flag to control adding new bookings
            // Process 1: Add new bookings
            while (proceed) {
                System.out.println("\nProceed with a new booking? (yes/no)");
                String response = sc.nextLine();
                if (response.equalsIgnoreCase("yes")) {
                    String lastBookingID = "B0"; // Default if queue is empty
                    Queue tempQueue = new Queue();
                    while (!theQueue.isEmpty()) {
                        cb = (CarBooking) theQueue.dequeue();
                        lastBookingID = cb.getBookingID();
                        tempQueue.enqueue(cb); // Restore the queue
                    }
                    while (!tempQueue.isEmpty()) {
                        theQueue.enqueue(tempQueue.dequeue());
                    }
                    int newBookingNumber = Integer.parseInt(lastBookingID.substring(1)) + 1;
                    String newBookingID = "B" + newBookingNumber;
                    System.out.println("Enter Customer Name:");
                    String newCustomerName = sc.nextLine();
                    System.out.println("Enter Car Model (e.g., Toyota Vellfire, Nissan Almera, Toyota Hilux, Honda Accord, Toyota Camry):");
                    String newCarModel = sc.nextLine();
                    System.out.println("Enter Days Booked:");
                    int newDaysBooked = Integer.parseInt(sc.nextLine());
                    System.out.println("Enter Pickup Date (DD-MM-YYYY):");
                    String newPickupDate = sc.nextLine();
                    System.out.println("Enter Return Date (DD-MM-YYYY):");
                    String newReturnDate = sc.nextLine();
                    System.out.println("Enter Pickup/Return Time in 24-hour system (HHMM):");
                    int newTime = Integer.parseInt(sc.nextLine());
                    double pricePerDay = 0.0;
                    switch (newCarModel) {
                        case "Toyota Vellfire":
                            pricePerDay = 430.00;
                            break;
                        case "Nissan Almera":
                            pricePerDay = 90.00;
                            break;
                        case "Toyota Hilux":
                            pricePerDay = 170.00;
                            break;
                        case "Honda Accord":
                            pricePerDay = 143.00;
                            break;
                        case "Toyota Camry":
                            pricePerDay = 177.00;
                            break;
                        default:
                            System.out.println("Unknown car model. Please restart the process.");
                            continue; // Skip this iteration if the car model is invalid
                    }
                    double totalPaymentBooking = pricePerDay * newDaysBooked;
                    String newStatus = "Confirmed";
                    CarBooking newBooking = new CarBooking(newBookingID, newCustomerName, newCarModel, newPickupDate, newReturnDate, newTime, totalPaymentBooking, newDaysBooked, newStatus);
                    theQueue.enqueue(newBooking);
                    System.out.println("\nNew booking added successfully.");
                } else if (response.equalsIgnoreCase("no")) {
                    proceed = false; // Exit the loop
                } else {
                    System.out.println("Invalid response. Please enter 'yes' or 'no'.");
                }
            }
            // Process 2: Display all bookings
            System.out.println("\nAll bookings:");
            Queue tempQueue2 = new Queue(); // Temporary queue to preserve the original queue state
            while (!theQueue.isEmpty()) {
                cb = (CarBooking) theQueue.dequeue();
                System.out.println(cb.toString());
                tempQueue2.enqueue(cb); // Restore the queue
            }
            while (!tempQueue2.isEmpty()) {
                theQueue.enqueue(tempQueue2.dequeue());
            }
            // Process 3: Count occurrences of a specific car model
            System.out.println("\nEnter the car model to count:");
            String carModelToCount = sc.nextLine();
            int count = 0;
            Queue tempQueue3 = new Queue(); // Temporary queue to preserve the original queue state
            while (!theQueue.isEmpty()) {
                cb = (CarBooking) theQueue.dequeue();
                if (cb.getCarModel().equalsIgnoreCase(carModelToCount)) {
                    count++; // Increment count if car model matches
                }
                tempQueue3.enqueue(cb); // Restore the queue
            }
            System.out.println("The car model " + carModelToCount + " appears " + count + " time(s) in the list.");
            while (!tempQueue3.isEmpty()) {
                theQueue.enqueue(tempQueue3.dequeue());
            }
            // Process 4: Remove a booking by ID
            System.out.println("\nEnter the Booking ID to remove:");
            String bookingIDToRemove = sc.nextLine();
            boolean removed = false;
            Queue tempQueue4 = new Queue();

            while (!theQueue.isEmpty()) {
                cb = (CarBooking) theQueue.dequeue();
                if (cb.getBookingID().equals(bookingIDToRemove)) {
                    removed = true; // Mark booking as removed
                } else {
                    tempQueue4.enqueue(cb);
                }
            }
            if (removed) {
                System.out.println("\nBooking with ID " + bookingIDToRemove + " removed successfully.");
                theQueue = tempQueue4; // Update the queue with the new state
            } else {
                System.out.println("\nBooking with ID " + bookingIDToRemove + " not found.");
            }
            // Process 5: Find the best-selling car model
            if (theQueue.isEmpty()) {
                System.out.println("\nNo car bookings available.");
            } else {
                String bestSeller = ""; // Placeholder for the best-selling model
                int maxCount = 0;
                Queue tempQueue5 = new Queue();
                while (!theQueue.isEmpty()) {
                    CarBooking currentBooking = (CarBooking) theQueue.dequeue();
                    String currentModel = currentBooking.getCarModel();
                    int modelCount = 0;
                    Queue tempQueue6 = new Queue(); // Another temporary queue to restore the original queue state
                    while (!theQueue.isEmpty()) {
                        CarBooking booking = (CarBooking) theQueue.dequeue();
                        if (booking.getCarModel().equalsIgnoreCase(currentModel)) {
                            modelCount++; // Increment the count if the model matches
                        }
                        tempQueue6.enqueue(booking); // Restore the original queue
                    }
                    while (!tempQueue6.isEmpty()) {
                        theQueue.enqueue(tempQueue6.dequeue());
                    }
                    if (modelCount > maxCount) {
                        bestSeller = currentModel;
                        maxCount = modelCount;
                    }
                    tempQueue5.enqueue(currentBooking);
                }
                while (!tempQueue5.isEmpty()) {
                    theQueue.enqueue(tempQueue5.dequeue());
                }
                System.out.println("Best-selling car model: " + bestSeller + " (Sold: " + maxCount + ")");
            }
            // Process 6: Calculate total payments
            double totalPayment = 0.0;
            Queue tempQueue7 = new Queue();
            while (!theQueue.isEmpty()) {
                cb = (CarBooking) theQueue.dequeue();
                totalPayment += cb.getPrice(); // Add each booking's price to total payment
                tempQueue7.enqueue(cb); // Restore the main queue
            }
            while (!tempQueue7.isEmpty()) {
                theQueue.enqueue(tempQueue7.dequeue());
            }
            System.out.println("\nTotal payment for all bookings: RM" + totalPayment);
            // Process 7: Update booking status
            System.out.print("\nEnter Booking ID to update status: ");
            String bookingIDToUpdate = sc.nextLine();
            System.out.print("Enter new status for the booking: ");
            String updatedStatus = sc.nextLine();
            Queue tempQueue8 = new Queue(); // Temporary queue to preserve the original queue order
            boolean isUpdated = false;
            while (!theQueue.isEmpty()) {
                cb = (CarBooking) theQueue.dequeue(); // Dequeue the booking from the main queue
                if (cb.getBookingID().equals(bookingIDToUpdate)) {
                    cb.setStatus(updatedStatus); // Update the status
                    isUpdated = true;
                }
                tempQueue8.enqueue(cb); // Enqueue the booking into the temporary queue to preserve order
            }
            if (isUpdated) {
                System.out.println("\nBooking status updated successfully!");
            } else {
                System.out.println("\nBooking ID not found.");
            }
            System.out.println("\nAll bookings after update:");
            Queue displayQueue = new Queue();
            while (!tempQueue8.isEmpty()) {
                cb = (CarBooking) tempQueue8.dequeue();
                System.out.println(cb.toString()); // Display each booking
                displayQueue.enqueue(cb); // Restore the queue
            }
            while (!displayQueue.isEmpty()) {
                theQueue.enqueue(displayQueue.dequeue());
            }
            // Process 8: Filter bookings by status
            while (true) { // Infinite loop for repeating the process
                System.out.print("\nDo you want to display bookings by status? (yes/no): ");
                String filterResponse = sc.nextLine();

                if (filterResponse.equalsIgnoreCase("yes")) {
                    System.out.print("Enter status to filter (Cancelled, Confirmed, Completed): ");
                    String statusToFilter = sc.nextLine();
                    System.out.println("\nBookings with status: " + statusToFilter);
                    boolean found = false;
                    Queue tempQueue9 = new Queue();
                    while (!theQueue.isEmpty()) {
                        cb = (CarBooking) theQueue.dequeue();
                        if (cb.getStatus().equalsIgnoreCase(statusToFilter)) {
                            System.out.println("\nBooking ID: " + cb.getBookingID() + " \nCustomer: " + cb.getCustName() +
                                               " \nCar Model: " + cb.getCarModel() + " \nDays Booked: " + cb.getDay() +
                                               " \nTotal Price: RM" + cb.getPrice());
                            found = true;
                        }
                        tempQueue9.enqueue(cb); // Enqueue the booking back to the temporary queue
                    }
                    while (!tempQueue9.isEmpty()) {
                        theQueue.enqueue(tempQueue9.dequeue());
                    }
                    if (!found) {
                        System.out.println("No bookings found with status: " + statusToFilter);
                    }
                } else if (filterResponse.equalsIgnoreCase("no")) {
                    break; // Exit the loop if the user enters "no"
                } else {
                    System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                }
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("Input file does not exist.");
        } catch (EOFException eof) {
            System.out.println(eof.getMessage());
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }
}
//LinkedListApp
// Import necessary packages
import java.util.*;  // Provides classes like LinkedList, StringTokenizer, Scanner, etc.
import java.io.*;    // Provides classes for file handling like BufferedReader, FileReader, etc.
public class LinkedListApp {
    public static void main(String[] args) {
        LinkedList carLL = new LinkedList();
        CarBooking cb;
        try (BufferedReader br = new BufferedReader(new FileReader("CarBooking.txt")); // Reading the input file
             Scanner sc = new Scanner(System.in)) { // Reading user input
            String record;
            while ((record = br.readLine())!= null) {
                StringTokenizer st = new StringTokenizer(record, ";");
                String bID = st.nextToken();            // Booking ID
                String c = st.nextToken();              // Customer Name
                String cM = st.nextToken();             // Car Model
                int d = Integer.parseInt(st.nextToken()); // Days Booked
                String pD = st.nextToken();             // Pickup Date
                String rD = st.nextToken();             // Return Date
                int T = Integer.parseInt(st.nextToken()); // Time (24-hour format)
                double p = Double.parseDouble(st.nextToken()); // Price
                String s = st.nextToken();              // Booking Status
                cb = new CarBooking(bID, c, cM, pD, rD, T, p, d, s);
                carLL.insertAtBack(cb);
            }
            boolean proceed = true; 
            while (proceed) {
                System.out.println("\nProceed with a new booking? (yes/no)");
                String response = sc.nextLine();
                if (response.equalsIgnoreCase("yes")) {
                    String lastBookingID = "B0"; // Default if the list is empty
                    Node current = carLL.getFirstNode(); // Start from the first node
                    while (current != null) {
                        lastBookingID = ((CarBooking) current.data).getBookingID(); // Get the last booking ID
                        current = current.next; // Move to the next node
                    }
                    int newBookingNumber = Integer.parseInt(lastBookingID.substring(1)) + 1;
                    String newBookingID = "B" + newBookingNumber;
                    System.out.println("Enter Customer Name:");
                    String newCustomerName = sc.nextLine();
                    System.out.println("Enter Car Model (e.g., Toyota Vellfire, Nissan Almera, Toyota Hilux, Honda Accord or Toyota Camry):");
                    String newCarModel = sc.nextLine();
                    System.out.println("Enter Days Booked:");
                    int newDaysBooked = Integer.parseInt(sc.nextLine());
                    System.out.println("Enter Pickup Date (DD-MM-YYYY):");
                    String newPickupDate = sc.nextLine();
                    System.out.println("Enter Return Date (DD-MM-YYYY):");
                    String newReturnDate = sc.nextLine();
                    System.out.println("Enter Pickup/Return Time in 24-hour system (HHMM):");
                    int newTime = Integer.parseInt(sc.nextLine());
                    double pricePerDay = 0.0;
                    switch (newCarModel) {
                        case "Toyota Vellfire":
                            pricePerDay = 430.00;
                            break;
                        case "Nissan Almera":
                            pricePerDay = 90.00;
                            break;
                        case "Toyota Hilux":
                            pricePerDay = 170.00;
                            break;
                        case "Honda Accord":
                            pricePerDay = 143.00;
                            break;
                        case "Toyota Camry":
                            pricePerDay = 177.00;
                            break;
                        default:
                            System.out.println("Unknown car model. Please restart the process.");
                            continue; // Skip to the next iteration
                    }
                    String newStatus = "Confirmed";
                    double totalPrice = pricePerDay * newDaysBooked;
                    CarBooking newBooking = new CarBooking(newBookingID, newCustomerName, newCarModel, newPickupDate, newReturnDate, newTime, totalPrice, newDaysBooked, newStatus);
                    carLL.insertAtBack(newBooking);
                    System.out.println("\nNew booking added successfully.");
                } else if (response.equalsIgnoreCase("no")) {
                    proceed = false; // Stop adding new bookings
                } else {
                    System.out.println("Invalid response. Please enter 'yes' or 'no'.");
                }
            }

            // Process 2: Display all bookings
            System.out.println("\nAll bookings:");
            carLL.displayAll();
            // Process 3: Count occurrences of a specific car model
            System.out.print("\nEnter car model to count: ");
            String carModelToCount = sc.nextLine();
            int count = 0;
            Node current = carLL.getFirstNode(); // Start from the first node
            while (current != null) {
                cb = (CarBooking) current.data; // Retrieve the data stored in the current node of the queue (current.data)
                if (cb.getCarModel().equalsIgnoreCase(carModelToCount)) {
                    count++;
                }
                current = current.next; // Move to the next node
            }
            System.out.println("The car model " + carModelToCount + " appears " + count + " time(s) in the list.");
            // Process 4: Remove a booking by ID
            System.out.print("\nEnter Booking ID to remove: ");
            String bookingIDToRemove = sc.nextLine();
            current = carLL.getFirstNode(); // Start from the first node
            Node prev = null; // Keep track of the previous node
            boolean removed = false;
            while (current != null) {
                cb = (CarBooking) current.data;
                if (cb.getBookingID().equals(bookingIDToRemove)) {
                    if (prev == null) {
                        carLL.first = current.next; // Remove the first node
                    } else {
                        prev.next = current.next; // Remove the current node
                    }
                    removed = true;
                    break;
                }
                prev = current;
                current = current.next;
            }
            if (removed) {
                System.out.println("Booking with ID " + bookingIDToRemove + " removed successfully.");
            } else {
                System.out.println("Booking with ID " + bookingIDToRemove + " not found.");
            }
            System.out.println("\nUpdated bookings after removal:");
            carLL.displayAll();
            // Process 5: Find the best-selling car model
            if (carLL.isEmpty()) {
                System.out.println("\nNo car bookings available.");
            } else {
                String bestSeller = ""; // It is set to an empty string as a placeholder until the best-selling model is determined.
                int maxCount = 0;
                current = carLL.getFirstNode();
                while (current != null) {
                    String currentModel = ((CarBooking) current.data).getCarModel();
                    int modelCount = 0;
                    Node temp = carLL.getFirstNode(); // Start from the first node
                    while (temp != null) {
                        if (((CarBooking) temp.data).getCarModel().equalsIgnoreCase(currentModel)) {
                            modelCount++;
                        }
                        temp = temp.next;
                    }
                    if (modelCount > maxCount) {
                        bestSeller = currentModel;
                        maxCount = modelCount;
                    }
                    current = current.next;
                }
                System.out.println("Best-selling car model: " + bestSeller + " (Sold: " + maxCount + ")");
            }
            // Process 6: Calculate total payments
            double totalPayment = 0.0;
            current = carLL.getFirstNode();
            while (current != null) {
                cb = (CarBooking) current.data;
                double bookingPayment = cb.getPrice();
                totalPayment += bookingPayment;
                current = current.next;
            }
            System.out.println("\nTotal payment for all bookings: RM" + totalPayment);
            // Process 7: Update booking status
            System.out.print("\nEnter Booking ID to update status: ");
            String bookingIDToUpdate = sc.nextLine();
            System.out.print("Enter new status for the booking: ");
            String updatedStatus = sc.nextLine();
            current = carLL.getFirstNode();
            boolean isUpdated = false;
            while (current != null) {
                cb = (CarBooking) current.data;
                if (cb.getBookingID().equals(bookingIDToUpdate)) {
                    cb.setStatus(updatedStatus); // Update status
                    isUpdated = true;
                    break;
                }
                current = current.next;
            }
            if (isUpdated) {
                System.out.println("\nBooking status updated successfully!");
            } else {
                System.out.println("\nBooking ID not found.");
            }
            System.out.println("\nAll bookings after update:");
            carLL.displayAll();
            // Process 8: Filter bookings by status
            while (true) { // Infinite loop for repeating the process
            System.out.print("\nDo you want to display bookings by status? (yes/no): ");
            String filterResponse = sc.nextLine();
            if (filterResponse.equalsIgnoreCase("yes")) {
            System.out.print("Enter status to filter (Cancelled, Confirmed, Completed): ");
            String statusToFilter = sc.nextLine();
            System.out.println("\nBookings with status: " + statusToFilter);
            current = carLL.getFirstNode(); // Start from the first node
            boolean found = false;
            while (current != null) {
            cb = (CarBooking) current.data; // Retrieve the CarBooking object
            if (cb.getStatus().equalsIgnoreCase(statusToFilter)) {
                System.out.println("\nBooking ID: " + cb.getBookingID() + " \nCustomer: " + cb.getCustName() +
                                   " \nCar Model: " + cb.getCarModel() + " \nDays Booked: " + cb.getDay() +
                                   " \nTotal Price: RM" + cb.getPrice());
                found = true;
            }
            current = current.next; // Move to the next node
            }
            if (!found) {
            System.out.println("No bookings found with status: " + statusToFilter);
            }
            } else if (filterResponse.equalsIgnoreCase("no")) {
            break; // Exit the loop if the user enters "no"
            } else {
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
            }

            } catch (FileNotFoundException fnf) {
                System.out.println("Input file does not exist.");
            } catch (IOException io) {
                System.out.println("An error occurred during file processing: " + io.getMessage());
            }
            }
            }
