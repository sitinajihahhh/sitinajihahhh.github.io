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
