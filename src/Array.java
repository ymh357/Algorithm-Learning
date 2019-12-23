public class Array {

    private int[] array;
    private int size;

    public Array(int capacity){
        this.array = new int[capacity];
    }
    public int getSize() {
        return size;
    }

    public boolean insert(int data){
        if(size < array.length){
            array[size] = data;
            size++;
            return true;
        }else if(size == array.length){
            int[] newArray = new int[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
            array[size] = data;
            size++;
            return true;
        }else{
            System.out.println("error: length > capacity.");
            return false;
        }
    }

    public boolean insert(int index, int data){
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
            int[] newArray = new int[array.length * 2];
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

    public void printArray(){
        for(int i = 0; i< size; i++){
            System.out.print(array[i]);
        }
        System.out.print('\n');
    }

    public static void main(String[] args) {
        Array array = new Array(3);
        array.insert(1);
        array.insert(2);
        array.insert(1,3);
        array.insert(1,4);
        array.delete();
        array.insert(99);
        array.printArray();
        array.insert(4);
        array.delete(1);
        array.insert(5);
        array.printArray();
    }
}
