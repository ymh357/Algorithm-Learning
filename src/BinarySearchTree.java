import com.sun.source.tree.BinaryTree;

public class BinarySearchTree<T extends Comparable<T>> extends LinkedBinaryTree<T> {

    public BinarySearchTree(LinkedList<T> initialLinkedList) {
        super(initialLinkedList);
        if(isValid()){
            System.out.println("Binary Tree is valid.");
        }else{
            System.out.println("Binary Tree is invalid.");
        }
    }

    public boolean isValid(){
        return _isValid(headNode);
    }

    private boolean _isValid(Node head){
        if(head == null){
            return true;
        }
        if(head.leftChild == null && head.rightChild == null){
            return  true;
        }

        Node left = head.leftChild;
        Node right = head.rightChild;

        boolean largerThanLeft = left == null || head.data.compareTo(left.data) > 0;
        boolean lessThanRight = right == null || head.data.compareTo(right.data) < 0;
        return  largerThanLeft && lessThanRight && _isValid(left) && _isValid(right);

    }

    public Node searchTree(T data){
        return _searchTree(data, headNode);
    }

    private Node _searchTree(T data, Node head){
        if(head == null){
            return null;
        }
        if(head.data.compareTo(data)==0){
            return head;
        }else {
            Node leftResult = _searchTree(data, head.leftChild);
            if (leftResult!=null){
                return leftResult;
            }else {
                return _searchTree(data, head.rightChild);
            }
        }
    }

    public void addData(T data){
         headNode= _addData(data, headNode);
    }

    private Node _addData(T data, Node node){
        if(node == null){
            Node newNode = new Node();
            newNode.data = data;
            newNode.leftChild = null;
            newNode.rightChild = null;
            return newNode;
        }
        if(data.compareTo(node.data) < 0){

            node.leftChild = _addData(data, node.leftChild);

        }else if(data.compareTo(node.data) > 0){

            node.rightChild = _addData(data, node.rightChild);
        }
        return node;
    }

    public void deleteData(T data){
        headNode = _deleteData(data, headNode);
    }

    private Node _deleteData(T data, Node head){
        Node node = searchTree(data);
        if(node == null || head == null){
            return head;
        }
        if(node.equals(head)){
            // Only root node exits in the tree.
            if(node.leftChild == null && node.rightChild == null){
                return null;
            }
        }
        Node parent = getParent(node);
        // Node is root.
        if(parent == null){
            if(!(node.rightChild != null && node.leftChild != null)){
                Node child = node.leftChild != null? node.leftChild : node.rightChild;
                return child;
            }
        }

        if(node.rightChild == null && node.leftChild == null){
            /*
             *  If no child.
             */

            if(node.equals(parent.leftChild)){
                parent.leftChild = null;
            }else{
                parent.rightChild = null;
            }
            return head;
        }else if(node.rightChild != null && node.leftChild != null){
            /*
             *  If has both left and right child.
             */

            T min = _getMin(node.rightChild);
            node.rightChild = _deleteData(min, node.rightChild);
            node.data = min;
            return head;
        }else {
            /*
             *  If has only one child.
             */
            Node child = node.leftChild != null? node.leftChild : node.rightChild;
            if(node.equals(parent.leftChild)){
                parent.leftChild = child;
            }else {
                parent.rightChild = child;
            }
            return head;
        }

    }

    private T _getMax(Node head){
        while (head.rightChild!=null){
            head = head.rightChild;
        }
        return head.data;
    }

    private T _getMin(Node head){
        while (head.leftChild!=null){
            head = head.leftChild;
        }
        return head.data;
    }


    public static void main(String[] args) {
        MyArray<Integer> array = new MyArray<>(11);
        array.buildArray(new Integer[]{5,2,-4,null,null,3,null,null,12,9,null,null,21,19,null,null,25,null,null});
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(new LinkedList<>(array));
        System.out.println("Found item: " + tree.searchTree(1));
        tree.deleteData(12);
        tree.deleteData(21);
        System.out.print(tree.printTree("breadth"));
        System.out.println("parent of 3 is: " + tree.getParent(tree.searchTree(3)).data);

    }
}
