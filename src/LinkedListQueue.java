public class LinkedListQueue<T> {
    private LinkedList<T> linkedList;

    public int getSize() {
        return linkedList.getSize();
    }

    public LinkedListQueue(T[] initialArray) {
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

    public boolean unshift(){
        return linkedList.delete(0);
    }

    public String printQueue(){
        return linkedList.printList();
    }


}
