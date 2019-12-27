import java.util.Arrays;
import java.util.Iterator;

public class ArrayQueue<T> implements Iterable<T>{

    private MyArray<T> array;
    private int headIndex = -1;
    private int size;

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    public ArrayQueue(int capacity) {
        array = new MyArray<>(capacity);
        for(int i=0; i<capacity; i++){
            array.insert(null);
        }
        size = 0;
    }

    public T getData(int index){
        if(index < 0 || index >= size){
            System.out.println("Error: index is < 0 or >= size");
            return null;
        }
        return array.getData((headIndex + index) % array.getCapacity());
    }

    public int searchData(T data){
        for(int i=0; i<size; i++){
            int index = (i + headIndex) % array.getCapacity();
            if(data.equals(array.getData(index))){
                return i;
            }
        }
        return -1;
    }

    public void push(T data){
        if (size == 0){
            headIndex = 0;
        }

        if(size < array.getCapacity()){
            int index = (headIndex + size) % array.getCapacity();
            array.update(index, data);
            size ++;
        }
        else{
            MyArray<T> newArray = new MyArray<>(array.getCapacity() * 2);
            for(int i = 0; i < newArray.getCapacity(); i ++){
                newArray.insert(null);
            }
            for(int i = 0; i < size; i ++){
                newArray.update(i, getData(i));
            }
            newArray.update(size, data);
            array = newArray;
            headIndex = 0;
            size ++;
        }
    }

    public boolean unshift(){
        if(size <= 0){
            return false;
        }
        headIndex = (headIndex + 1) % array.getCapacity();
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
        for(Integer i: arrayQueue){
            System.out.print(i);
        }
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            int count = 0;
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public T next() {
                T data = getData(count);
                count ++;
                return data;
            }
        };

    }
}
