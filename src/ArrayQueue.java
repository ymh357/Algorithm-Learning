public class ArrayQueue<T> {

    private T[] array;
    private int headIndex = -1;
    private int size;

    public int getSize() {
        return size;
    }

    public ArrayQueue(int capacity) {
        array = (T[]) new Object[capacity];
        size = 0;
    }

    public T getData(int index){
        if(index < 0 || index >= size){
            System.out.println("Error: index is < 0 or >= size");
            return null;
        }
        return array[(headIndex + index) % array.length];
    }

    public boolean searchData(T data){
        for(int i=0; i<size; i++){
            int index = (i + headIndex) % array.length;
            if(array[index] == data){
                return true;
            }
        }
        return false;
    }

    public void push(T data){
        if (size == 0){
            headIndex = 0;
        }

        if(size < array.length){
            int index = (headIndex + size) % array.length;
            array[index] = data;
            size ++;
        }
        else{
            T[] newArray = (T[]) new Object[array.length * 2];
            for(int i = 0; i < size; i ++){
                newArray[i] = getData(i);
            }
            newArray[size] = data;
            array = newArray;
            headIndex = 0;
            size ++;
        }
    }

    public boolean unshift(){
        if(size <= 0){
            return false;
        }
        headIndex = (headIndex + 1) % array.length;
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
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<Integer>(3);
        arrayQueue.push(1);
        arrayQueue.push(2);
        arrayQueue.unshift();
        arrayQueue.push(3);
        arrayQueue.push(4);
        arrayQueue.push(5);
        arrayQueue.push(6);
        arrayQueue.unshift();
        arrayQueue.push(7);
        arrayQueue.push(8);
        arrayQueue.unshift();

        System.out.print(arrayQueue.printQueue());
    }

}
