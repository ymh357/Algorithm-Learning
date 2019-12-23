public class ArrayStack {

    private Array array;

    public int getSize() {
        return array.getSize();
    }

    public ArrayStack(int capacity) {
        array = new Array(capacity);
    }

    public int getData(int index){
        return array.getData(index);
    }

    public boolean searchData(int data){
        return array.searchArray(data);
    }

    public void push(int data){
        array.insert(data);
    }

    public boolean pop(){
        return array.delete();
    }

    public String printStack(){
        return array.printArray();
    }


}
