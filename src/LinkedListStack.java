import java.util.Iterator;

public class LinkedListStack<T> implements Iterable<T> {
    private LinkedList<T> linkedList;

    public int getSize() {
        return linkedList.getSize();
    }

    public LinkedListStack(MyArray<T> initialArray) {
        linkedList = new LinkedList<T>(initialArray);
    }

    public T getData(int index){
        return linkedList.getData(index);
    }

    public int searchData(T data){
        return linkedList.searchList(data);
    }

    public void push(T data){
        linkedList.insert(data);
    }

    public boolean pop(){
        return linkedList.delete();
    }

    public String printStack(){
        return linkedList.printList();
    }

    @Override
    public Iterator<T> iterator() {
        return linkedList.iterator();
    }
}
