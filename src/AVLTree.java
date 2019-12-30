import java.util.Objects;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    public AVLTree(LinkedList<T> initialLinkedList) {
        super(initialLinkedList);
    }

    public boolean isValid(){
        boolean b1 = super.isValid();
        boolean b2 = supervise() == null;
        return b1 && b2;
    }

    public void addData(T data){
        super.addData(data);
        Node node = supervise();
        _adjustTree(node);
    }

        private void _adjustTree(Node node){
        Node parent = getParent(node);
        switch (calculateRotateType(node)){
            case "right":
                if(parent == null){
                    headNode = _rightRotate(headNode);
                }else {
                    if(parent.leftChild.equals(node)){
                        parent.leftChild = _rightRotate(node);
                    }else{
                        parent.rightChild = _rightRotate(node);
                    }
                }
                break;
            case "leftThenRight":
                if(parent == null){
                    headNode = _leftThenRightRotate(headNode);
                }else {
                    if(parent.leftChild.equals(node)){
                        parent.leftChild = _leftThenRightRotate(node);
                    }else{
                        parent.rightChild = _leftThenRightRotate(node);
                    }
                }
                break;
            case "left":
                if(parent == null){
                    headNode = _leftRotate(headNode);
                }else {
                    if(parent.leftChild.equals(node)){
                        parent.leftChild = _leftRotate(node);
                    }else{
                        parent.rightChild = _leftRotate(node);
                    }
                }
                break;
            case "rightThenLeft":
                if(parent == null){
                    headNode = _rightThenLeftRotate(headNode);
                }else {
                    if(parent.leftChild.equals(node)){
                        parent.leftChild = _rightThenLeftRotate(node);
                    }else{
                        parent.rightChild = _rightThenLeftRotate(node);
                    }
                }
                break;
        }
    }

    public void deleteData(T data){
        super.deleteData(data);
        Node node = supervise();
        _adjustTree(node);
    }

    private String calculateRotateType(Node node){
        if(_getLevels(node.leftChild) > _getLevels(node.rightChild)){
            if(_getLevels(node.leftChild.leftChild) > _getLevels(node.leftChild.rightChild)){
                // If insert into leftchild's left child tree.
                return "right";
            }else{
                return "leftThenRight";
            }
        }else{
            if(_getLevels(node.rightChild.rightChild) > _getLevels(node.rightChild.leftChild)){
                // If insert into rightchild's right child tree.
                return "left";
            }else{
                return "rightThenLeft";
            }
        }
    }

    private Node _rightRotate(Node head){
        Node newNode = head.leftChild;
        head.leftChild = newNode.rightChild;
        newNode.rightChild = head;

        return newNode;
    }
    private Node _leftRotate(Node head){
        Node newNode = head.rightChild;
        head.rightChild = newNode.leftChild;
        newNode.leftChild = head;

        return newNode;
    }
    private Node _rightThenLeftRotate(Node head){
        head.rightChild = _rightRotate(head.rightChild);
        return _leftRotate(head);
    }
    private Node _leftThenRightRotate(Node head){
        head.leftChild = _leftRotate(head.leftChild);
        return _rightRotate(head);
    }

    public Node supervise(){
        return _supervise(headNode);
    }

    private Node _supervise(Node head){

        if(_getLevels(head) <= 2){
            return null;
        }
        Node result = null;
        if(Math.abs(_getLevels(head.leftChild) - _getLevels(head.rightChild)) >= 2){
           result = head;
        }

        Node leftResult = _supervise(head.leftChild);
        Node rightResult = _supervise(head.rightChild);
        if(leftResult != null){
            result = leftResult;
        }else if(rightResult != null){
            result = rightResult;
        }
        return result;
    }

    public static void main(String[] args) {
        MyArray<Integer> array = new MyArray<>(11);
        array.buildArray(new Integer[]{5,2,-4,null,null,3,null,null,12,9,null,null,21,19,null,null,25,null,null});
        AVLTree<Integer> tree = new AVLTree<>(new LinkedList<>(array));
        System.out.print(tree.printTree("breadth"));
        tree.deleteData(9);
        System.out.print(tree.printTree("breadth"));
        System.out.print("is valid? " + tree.isValid());
    }
}
