import java.util.HashMap;

public class MyHashMap<K, V> {

    private MyArray<LinkedList<Pair<K, V>>> array;
    private final float LoadFactor = 0.75F;

    public int getSize(){
        int size = 0;
        for(LinkedList<Pair<K, V>> ll: array){
            if(ll != null)
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

    public int getHash(K key){
        return key.hashCode() % array.getCapacity();
    }

    public V get(K key){
        int index = getHash(key);
        for(Pair<K,V> pair: array.getData(index)){
            if(key.equals(pair.key)){
                return pair.value;
            }
        }
        System.out.print("Error: Key not found.");
        return null;
    }

    public void put(K key, V value){

        if(getSize() >= array.getCapacity() * LoadFactor){
            int index = getHash(key);
            boolean no_resize = false;
            if(array.getData(index) != null){
                // Index in use.
                LinkedList<Pair<K, V>> linkedList = array.getData(index);
                int pos = linkedList.searchList(new Pair<K, V>(key, value));
                if(pos != -1){
                    // key in use.
                    no_resize = true;
                }
            }
            if(!no_resize){
                resize();
            }
        }

        _put(key, value);
    }

    private void _put(K key, V value){

        int index = getHash(key);
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
                // If same key, then replace a with b.
                linkedList.update(pos, new Pair<K, V>(key, value));
            }else{
                linkedList.insert(new Pair<K, V>(key, value));
            }
        }
    }

    private void resize() {

        MyArray<Pair<K,V>> pairArray = new MyArray<Pair<K,V>>(getSize());
        for(LinkedList<Pair<K, V>> ll: array){
            if(ll == null){
                continue;
            }
            for(Pair<K,V> pair: ll){
                pairArray.insert(pair);
            }
        }

        MyArray<LinkedList<Pair<K, V>>> newArray = new MyArray<LinkedList<Pair<K, V>>>(array.getCapacity() * 2);
        for(int i=0; i < newArray.getCapacity(); i++){
            newArray.insert(null);
        }

        array = newArray;
        for(Pair<K,V> pair: pairArray){
            _put(pair.key, pair.value);
        }
    }

    public boolean remove(K key){
        int index = getHash(key);
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
        myHashMap.put(7,"sth");
        myHashMap.put(8,"yuanchu");
        myHashMap.put(9, "sth");
        myHashMap.put(8,"yuanchu2");
        System.out.print(myHashMap.getSize());
        myHashMap.get(8);
        myHashMap.remove(9);

    }
}
