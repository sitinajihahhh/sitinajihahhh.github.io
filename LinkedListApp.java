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