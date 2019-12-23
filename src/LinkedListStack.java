public class LinkedListStack {
    private LinkedList linkedList;

    public int getSize() {
        return linkedList.getSize();
    }

    public LinkedListStack(int[] initialArray) {
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

    public boolean pop(){
        return linkedList.delete();
    }

    public String printStack(){
        return linkedList.printList();
    }
}
