public class ArrayQueue {

    private int[] array;
    private int headIndex = -1;
    private int size;
    private int capacity;

    public int getSize() {
        return size;
    }

    public ArrayQueue(int capacity) {
        array = new int[capacity];
        this.capacity = capacity;
        size = 0;
    }

    public int getData(int index){
        return array[(headIndex + index) % capacity];
    }

    public boolean searchData(int data){
        for(int i=0; i<size; i++){
            int index = (i + headIndex) % capacity;
            if(array[index] == data){
                return true;
            }
        }
        return false;
    }

    public void push(int data){
        if (size == 0){
            headIndex = 0;
        }

        if(size < capacity){
            int index = (headIndex + size) % capacity;
            array[index] = data;
            size ++;
        }
        else{
            int[] newArray = new int[capacity*2];
            for(int i = 0; i < size; i ++){
                newArray[i] = getData(i);
            }
            newArray[size] = data;
        }
    }

    public boolean unshift(){
        if(size == 0){
            return false;
        }
        headIndex = (headIndex + 1) % capacity;
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
        ArrayQueue arrayQueue = new ArrayQueue(3);
        arrayQueue.push(1);
        arrayQueue.push(2);
        arrayQueue.push(3);
        arrayQueue.unshift();
        arrayQueue.push(4);

        System.out.print(arrayQueue.printQueue());
    }

}
