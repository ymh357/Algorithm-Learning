import java.util.Arrays;
import java.util.Iterator;

public class ArrayQueue<T> implements Iterable<T>{

    private T[] array;
    private int headIndex = -1;
    private int size;
    private int capacity;

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayQueue(int capacity) {
        array = (T[]) new Object[capacity];
        this.capacity = capacity;
        size = 0;
    }

    public T getData(int index){
        if(index < 0 || index >= size){
            System.out.println("Error: index is < 0 or >= size");
            return null;
        }
        return array[(headIndex + index) % capacity];
    }

    public int searchData(T data){
        for(int i=0; i<size; i++){
            int index = (i + headIndex) % capacity;
            if(data.equals(array[index])){
                return i;
            }
        }
        return -1;
    }

    public void push(T data){
        if (size == 0){
            headIndex = 0;
        }

        if(size < capacity){
            int index = (headIndex + size) % capacity;
            array[index] = data;
            size ++;
        }
        else{
            T[] newArray = (T[]) new Object[capacity * 2];
            for(int i = 0; i < size; i ++){
                newArray[i] = getData(i);
            }
            newArray[size] = data;
            array = newArray;
            capacity *= 2;
            headIndex = 0;
            size ++;
        }
    }

    public boolean unshift(){
        if(size <= 0){
            return false;
        }
        headIndex = (headIndex + 1) % capacity;
        size --;
        return true;
    }

    public String printQueue(){
        String out = "";
        for(int i = 0; i< size; i++){
            out += getData(i) + ", ";
        }
        out += '\n';
        return out;
    }


    public static void main(String[] args) {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<Integer>(3);
        arrayQueue.push(1);
        arrayQueue.push(2);
        arrayQueue.unshift();
        arrayQueue.push(3);
        arrayQueue.push(4);
        arrayQueue.push(5);
        arrayQueue.push(6);
        arrayQueue.unshift();
        arrayQueue.push(7);
        arrayQueue.push(8);
        arrayQueue.unshift();

        System.out.print(arrayQueue.printQueue());
    }

    @Override
    public Iterator<T> iterator() {
        T[] realArray = (T[])new Object[size];
        for(int i=0; i<size; i++){
            realArray[i] = getData(i);
        }
        return Arrays.stream(realArray).iterator();
    }
}
