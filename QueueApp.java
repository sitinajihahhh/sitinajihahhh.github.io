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