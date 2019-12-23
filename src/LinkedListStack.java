public class LinkedListStack<T> {
    private LinkedList<T> linkedList;

    public int getSize() {
        return linkedList.getSize();
    }

    public LinkedListStack(T[] initialArray) {
        linkedList = new LinkedList<T>(initialArray);
    }

    public T getData(int index){
        return linkedList.getData(index);
    }

    public boolean searchData(T data){
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
}
