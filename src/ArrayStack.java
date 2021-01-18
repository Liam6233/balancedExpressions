import java.util.EmptyStackException;

public class ArrayStack<E> {
    E[] myArray;

    /*
     * using the Java generic type E. This way, you can make a new
     * ArrayStack<Integer>, for example, which will automatically
     * accept and return Integers instead of generic objects.
     */
    @SuppressWarnings("unchecked")  // the cast to E[] in the constructor is safe, I pinky promise
    public ArrayStack() {
        // Creates an empty Stack.
        myArray = (E[]) new Object[0];
    }

    /**
     * Tests if this stack is empty.
     * @return true if and only if this stack contains no items; false otherwise.
     */
    public boolean empty() {
        return myArray.length == 0;
    }

    /**
     * Looks at the object at the top of this stack without removing it from the stack.
     * @return the object at the top of this stack.
     * @throws EmptyStackException  if this stack is empty.
     */
    public E peek() {
        int len = myArray.length;
        if (len == 0)
            throw new EmptyStackException();
        return myArray[len - 1];
    }

    /**
     * Removes the object at the top of this stack and returns that object as the value of this function.
     * @return The object at the top of this stack.
     * @throws EmptyStackException  if this stack is empty.
     */
    @SuppressWarnings("unchecked")
    public E pop() {
        int len = myArray.length;
        E oldObj = peek();
        E[] newArray = (E[]) new Object[len - 1];
        System.arraycopy(myArray, 0, newArray, 0, len - 1);
        myArray = newArray;
        return oldObj;
    }

    /**
     * Pushes an item onto the top of this stack.
     * @param item the item to be pushed onto this stack.
     * @return the item argument.
     */
    @SuppressWarnings("unchecked")
    public E push(E item) {
        int len = myArray.length;
        E[] newArray = (E[]) new Object[len + 1];
        System.arraycopy(myArray, 0, newArray, 0, len);
        newArray[len] = item;
        myArray = newArray;
        return item;
    }

    /**
     * Returns the 1-based position where an object is on this stack. If the object o occurs as an item in this stack, this method returns the distance from the top of the stack of the occurrence nearest the top of the stack; the topmost item on the stack is considered to be at distance 1. The equals method is used to compare o to the items in this stack.
     * @param o the desired object.
     * @return the 1-based position from the top of the stack where the object is located; the return value -1 indicates that the object is not on the stack.
     */
    public int search(Object o) {
        int len = myArray.length;
        if (o == null){
            for(int i = len - 1; i >= 0; i--)
                if (myArray[i] == null)
                    return len - i;
        } else {
            for(int i = len - 1; i >= 0; i--)
                if (o.equals(myArray[i]))
                    return len - i;
        }
        return -1;
    }

    /*
     * Test printing method.
     */
    public void print() {
        for (E obj : myArray)
            System.out.println(obj);
    }
}