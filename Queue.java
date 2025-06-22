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
