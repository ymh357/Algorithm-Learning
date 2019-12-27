import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

public class MyArray<T> implements Iterable<T> {

    private T[] array;
    private int size;
    private int capacity;

    public MyArray(int capacity){
        if(capacity <= 0){
            System.out.print("Error: capacity <= 0.");
        }
        this.array = (T[]) new Object[capacity];
        this.capacity = capacity;
        size = 0;
    }

    public void buildArray(T[] arr){
        for(T ele: arr){
            insert(ele);
        }
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void insert(T data){
        if(size < array.length){
            array[size] = data;
            size++;
        }else if(size == capacity){
            capacity *= 2;
            T[] newArray = (T[]) new Object[capacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
            array[size] = data;
            size++;
        }else{
            System.out.println("error: length > capacity.");
        }
    }

    public boolean insert(int index, T data){
        if(index >= size){
            return false;
        }
        if(size < capacity){
            for(int i = size - 1; i >= index; i--){
                array[i+1] = array[i];
            }
            array[index] = data;
            size++;
            return true;
        }else if(size == capacity){
            capacity *= 2;
            T[] newArray = (T[]) new Object[capacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
            for(int i = size - 1; i >= index; i--){
                array[i+1] = array[i];
            }
            array[index] = data;
            size++;
            return true;
        }else{
            System.out.println("error: length > capacity.");
            return false;

        }
    }

    public boolean delete(int index){
        if(index >= size){
            return false;
        }

        for(int i = index; i < size - 1; i++){
            array[i] = array[i+1];
        }
        size--;
        return true;
    }

    public boolean delete(){
        if(size == 0){
            return false;
        }
        size--;
        return true;
    }

    public T getData(int index){
        if(index >= size || index < 0){
            System.out.println("Error: index is < 0 or >= size");
            return null;
        }
        return array[index];
    }

    public boolean update(int index, T data){
        if(index >= size || index < 0){
            System.out.println("Error: index is < 0 or >= size");
            return false;
        }
        array[index] = data;
        return true;
    }

    public int searchArray(T data){
        for(int i=0; i<size; i++){
            if(data.equals(array[i])){
                return i;
            }
        }
        return -1;
    }

    public String printArray(){
        String out = "";
        for(int i = 0; i< size; i++){
            out += array[i] + ", ";
        }
        out += '\n';
        return out;
    }

    public static void main(String[] args) {
        MyArray<Integer> array = new MyArray<Integer>(3);
        array.insert(1);
        array.insert(2);
        array.insert(1,3);
        array.insert(1,4);
        array.delete();
        array.insert(99);
        System.out.print(array.printArray());
        array.insert(4);
        array.delete(1);
        array.insert(5);
        array.insert(6);
        array.insert(7);
        array.insert(8);
        array.insert(9);

        System.out.print(array.printArray());

        for(Integer arr: array){
            System.out.print(arr);
        }
    }

    @Override
    public Iterator<T> iterator() {
        T[] realArray = (T[])new Object[size];
        for(int i=0; i<size; i++){
            realArray[i] = array[i];
        }
        return Arrays.stream(realArray).iterator();
    }
}
