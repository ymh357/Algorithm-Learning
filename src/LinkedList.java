public class LinkedList<T> {

    private Node head;
    private Node tail;

    private int size;

    public int getSize() {
        return size;
    }

    public LinkedList(T[] initialArray){

        if(initialArray == null || initialArray.length == 0){
            head = null;
            tail = null;
            size = 0;
            return;
        }

        head = new Node(initialArray[0], null);
        Node previous = head;
        Node next = null;
        for(int i = 1; i < initialArray.length; i++){
            next = new Node(initialArray[i], null);
            previous.next = next;
            previous = next;
        }
        next.next = null;
        tail = next;
        size = initialArray.length;
    }

    public boolean searchList(T data){
        for(Node next = head; next!=null; next=next.next){
            if(next.data == data){
                return true;
            }
        }
        return false;
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

    private class Node{
        public T data;
        public Node next;

        public Node(T data, Node next){
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> ll = new LinkedList<Integer> (new Integer[]{1,2,3,4,5});
        ll.delete();
        ll.delete();
        ll.delete();
        ll.insert(9);
        System.out.println(ll.printList());
        System.out.println(ll.getSize());
    }
}
