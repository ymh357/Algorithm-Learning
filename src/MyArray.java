import java.lang.reflect.Array;

public class MyArray<T> {

    private T[] array;
    private int size;

    public MyArray(int capacity){
        if(capacity <= 0){
            System.out.print("Error: capacity <= 0.");
        }
        this.array = (T[]) new Object[capacity];
        size = 0;
    }
    public int getSize() {
        return size;
    }

    public void insert(T data){
        if(size < array.length){
            array[size] = data;
            size++;
        }else if(size == array.length){
            T[] newArray = (T[]) new Object[array.length * 2];
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
        if(size < array.length){
            for(int i = size - 1; i >= index; i--){
                array[i+1] = array[i];
            }
            array[index] = data;
            size++;
            return true;
        }else if(size == array.length){
            T[] newArray = (T[]) new Object[array.length * 2];
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

    public boolean searchArray(T data){
        for(int i=0; i<size; i++){
            if(array[i] == data){
                return true;
            }
        }
        return false;
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
        System.out.print(array.printArray());
    }
}
