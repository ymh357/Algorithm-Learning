import java.util.Iterator;

public class ArrayStack <T> implements Iterable<T>{

    private MyArray<T> array;

    public int getSize() {
        return array.getSize();
    }

    public ArrayStack(int capacity) {
        array = new MyArray(capacity);
    }

    public T getData(int index){
        return array.getData(index);
    }

    public int searchData(T data){
        return array.searchArray(data);
    }

    public void push(T data){
        array.insert(data);
    }

    public boolean pop(){
        return array.delete();
    }

    public String printStack(){
        return array.printArray();
    }


    @Override
    public Iterator<T> iterator() {
        return array.iterator();
    }
}
