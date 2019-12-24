public class MyHashMap<K, V> {

    private MyArray<LinkedList<Pair<K, V>>> array;

    public int getSize(){
        int size = 0;
        for(LinkedList<Pair<K, V>> ll: array){
            size += ll.getSize();
        }
        return size;
    }

    public MyHashMap(int capacity){
        array = new MyArray<LinkedList<Pair<K, V>>>(capacity);
        for(int i=0; i < capacity; i++){
            array.insert(null);
        }
    }

    public V get(K key){
        int index = key.hashCode() % array.getCapacity();
        for(Pair<K,V> pair: array.getData(index)){
            if(key.equals(pair.key)){
                return pair.value;
            }
        }
        System.out.print("Error: Key not found.");
        return null;
    }

    public void put(K key, V value){
        int index = key.hashCode() % array.getCapacity();
        if(array.getData(index) == null){
            // Index not used.
            LinkedList<Pair<K, V>> newLinkedList = new LinkedList<>(null);
            newLinkedList.insert(new Pair<K, V>(key, value));
            array.update(index, newLinkedList);
        }else{
            // Index in use.
            LinkedList<Pair<K, V>> linkedList = array.getData(index);
            int pos = linkedList.searchList(new Pair<K, V>(key, value));
            if(pos != -1){
                // If same index, then replace a with b.
                linkedList.update(pos, new Pair<K, V>(key, value));
            }else{
                linkedList.insert(new Pair<K, V>(key, value));
            }
        }
    }

    public boolean remove(K key){
        int index = key.hashCode() % array.getCapacity();
        int position = -1;
        boolean keyExist = false;
        for(Pair<K,V> pair: array.getData(index)){
            position ++;
            if(key.equals(pair.key)){
                keyExist = true;
                break;
            }
        }
        if(keyExist){
            array.getData(index).delete(position);
            return true;
        }
        System.out.print("Error: Key not found.");
        return false;
    }

    private class Pair<K, V>{
        public K key;
        public V value;

        public Pair(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            Pair<K, V> pb = (Pair<K, V>) obj;
            if(key.equals(pb.key)){
                return true;
            }
            return false;
        }

    }

    public static void main(String[] args) {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<Integer, String>(3);
        myHashMap.put(4,"yu");
        myHashMap.put(5,"ming");
        myHashMap.put(6,"hao");
        myHashMap.put(7,"zhang");
        myHashMap.put(8,"yuanchu");
        myHashMap.put(9,"wtf");
        myHashMap.put(8,"yuanchu2");
        System.out.print(myHashMap.getSize());
        myHashMap.get(8);
        myHashMap.remove(9);

    }
}
