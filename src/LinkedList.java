public class LinkedList {

    private Node head;
    private Node tail;

    private int length;

    public int getLength() {
        return length;
    }

    public LinkedList(int[] initialArray){

        tail = new Node(initialArray[initialArray.length - 1],null);
        head = new Node(initialArray[0], null);
        Node previous = head;
        Node next = null;
        for(int i = 1; i < initialArray.length; i++){
            next = new Node(initialArray[i], null);
            previous.next = next;
            previous = next;
        }
        length = initialArray.length;
    }

    public boolean searchList(int data){
        for(Node next = head; next!=null; next=next.next){
            if(next.data == data){
                return true;
            }
        }
        return false;
    }

    public int getData(int index){
        int i = 0;
        for(Node next = head; next!=null; next=next.next){
            if(i == index){
                return next.data;
            }
            i++;
        }
        return -1;
    }

    public boolean insertData(int index, int data){
        int i = 0;
        for(Node next = head; next!=null; next=next.next){
            if(i == index){
                Node newNode = new Node(data, null);
                newNode.next = next.next;
                next.next = newNode;
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean deleteData(int index, int data){
        int i = 0;
        for(Node next = head; next!=null; next=next.next){
            if(i == index - 1){
                next.next = next.next.next;
            }
            i++;
        }
        return false;
    }

    public String printList(){
        String out= "";
        for(Node next = head; next!=null; next=next.next){
            out += Integer.toString(next.data) + ", ";
        }
        return out;
    }

    private class Node{
        public int data;
        public Node next;

        public Node(int data, Node next){
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList(new int[]{1,2,3,4,5});
        ll.deleteData(3,66);
        System.out.println(ll.printList());
        System.out.println(ll.getData(3));
    }
}
