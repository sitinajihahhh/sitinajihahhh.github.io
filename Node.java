public class Node {
    public CarBooking data; 
    public Node next; 
    public Node(CarBooking d) {
        this.data = d;  // Assign the provided CarBooking object to the 'data' field.
        this.next = null; // Initialize the 'next' field to null.
    }
}
