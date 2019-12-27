import java.util.Iterator;

public class LinkedList<T> implements Iterable<T>{

    private Node head;
    private Node tail;

    private int size;

    public int getSize() {
        return size;
    }

    public LinkedList(MyArray<T> initialArray){

        if(initialArray == null || initialArray.getSize() == 0){
            head = null;
            tail = null;
            size = 0;
            return;
        }

        head = new Node(initialArray.getData(0), null);
        Node previous = head;
        Node next = null;
        for(int i = 1; i < initialArray.getSize(); i++){
            next = new Node(initialArray.getData(i), null);
            previous.next = next;
            previous = next;
        }
        next.next = null;
        tail = next;
        size = initialArray.getSize();
    }

    public int searchList(T data){
        int position = -1;
        for(Node next = head; next!=null; next=next.next){
            position ++;
            if(data.equals(next.data)){
                return position;
            }
        }
        return -1;
    }

    public T getData(int index){
        int i = 0;
        for(Node next = head; next!=null; next=next.next){
            if(i == index){
                return next.data;
            }
            i++;
        }
        return null;
    }

    public boolean update(int index, T data){
        int i = 0;
        for(Node next = head; next!=null; next=next.next){
            if(i == index){
                next.data = data;
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean insert(int index, T data){

        // Justify head.
        if(index == 0){
            Node newNode = new Node(data, head);
            head = newNode;
            ++size;
            return true;
        }
        int i = 0;
        for(Node next = head; next!=null; next=next.next){
            if(i == index - 1){
                Node newNode = new Node(data, null);
                newNode.next = next.next;
                next.next = newNode;

                // Justify tail.
                if(index == size){
                    tail = tail.next;
                }
                ++size;
                return true;
            }
            i++;
        }
        return false;
    }

    public void insert(T data){
        Node newNode = new Node(data,null);
        if(tail == null){
            // Insert to empty list.
            head = newNode;
            tail =newNode;
            ++size;
            return;
        }
        tail.next = newNode;
        tail = newNode;
        ++size;
    }

    public boolean delete(int index){

        // Justify head.
        if(index == 0){
            head = head.next;
            --size;
            return true;
        }

        int i = 0;
        for(Node next = head; next!=null; next=next.next){
            if(i == index - 1){
                next.next = next.next.next;

                // Justify tail.
                if(index == size){
                    tail = next;
                }
                --size;
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean  delete(){
        if(size == 0){
            return false;
        }

        if(head == tail){
            // When only one element left.
            head = null;
            tail = null;
            size = 0;
            return true;
        }

        Node secondLastNode  = head;
        while(secondLastNode.next != tail ){
            secondLastNode=secondLastNode.next;
        }
        secondLastNode.next = null;
        tail = secondLastNode;
        size --;
        return true;
    }

    public String printList(){
        String out= "";
        for(Node next = head; next!=null; next=next.next){
            out += next.data + ", ";
        }
        out += '\n';
        return out;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {

                return iterator != null;
            }

            @Override
            public T next() {
                T data = iterator.data;
                iterator = iterator.next;
                return data;
            }
            private Node iterator = head;
        };
    }

    private class Node{
        public T data;
        public Node next;

        public Node(T data, Node next){
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> ll = new LinkedList<Integer> (null);

        ll.insert(9);
        ll.delete();
        ll.insert(10);
        ll.insert(9);
        ll.update(1,11);
        System.out.println(ll.printList());
        for(Integer i: ll){
            System.out.print(i);
        }
        ll.searchList(10);
    }
}
