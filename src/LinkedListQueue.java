public class LinkedListQueue {
    private LinkedList linkedList;

    public int getSize() {
        return linkedList.getSize();
    }

    public LinkedListQueue(int[] initialArray) {
        linkedList = new LinkedList(initialArray);
    }

    public int getData(int index){
        return linkedList.getData(index);
    }

    public boolean searchData(int data){
        return linkedList.searchList(data);
    }

    public void push(int data){
        linkedList.insert(data);
    }

    public boolean unshift(){
        return linkedList.delete(0);
    }

    public String printQueue(){
        return linkedList.printList();
    }


}
