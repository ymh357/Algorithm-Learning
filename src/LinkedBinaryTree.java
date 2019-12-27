public class LinkedBinaryTree<T> {

    private Node headNode;
    private int size;

    public LinkedBinaryTree(LinkedList<T> initialLinkedList){
        headNode = _createTree(initialLinkedList);
    }

    private Node _createTree(LinkedList<T> initialLinkedList){
        if(initialLinkedList == null || initialLinkedList.getSize() == 0){
            return null;
        }
        T data = initialLinkedList.getData(0);
        initialLinkedList.delete(0);
        if(data==null){
            return null;
        }

        Node node = new Node();
        node.data = data;
        size ++;
        node.leftChild = _createTree(initialLinkedList);
        node.rightChild = _createTree(initialLinkedList);

        return node;
    }

    public MyArray<T> preOrderTraverse(){
        MyArray<T> array = new MyArray<>(size);
        _preOrderTraverse(headNode, array);
        return array;
    }

    private void _preOrderTraverse(Node head, MyArray<T> array){
        if(head == null || array == null){
            return;
        }
        array.insert(head.data);
        _preOrderTraverse(head.leftChild, array);
        _preOrderTraverse(head.rightChild,array);
    }

    public MyArray<T> preOrderTraverse_stack(){
        MyArray<T> array = new MyArray<T>(size);
        LinkedListStack<Node> stack = new LinkedListStack<>(null);
        stack.push(headNode);
        while (stack.getSize() > 0){
            Node node = stack.getData(stack.getSize() - 1);
            stack.pop();
            array.insert(node.data);
            if(node.rightChild != null){
                stack.push(node.rightChild);
            }
            if(node.leftChild != null){
                stack.push(node.leftChild);
            }
        }
        return array;
    }

    public String printTree(String option){

        MyArray<T> array = null;
        switch (option){
            case "pre":
                array = preOrderTraverse_stack();
                break;
            case "in":
                array = inOrderTraverse();
                break;
            case "post":
                array = postOrderTraverse();
                break;
            case "breadth":
                array = breadthFirstTraverse();
        }
        if(array == null){
            return "Failed traverse.";
        }
        return array.printArray();
    }

    public MyArray<T> breadthFirstTraverse() {
        MyArray<T> array = new MyArray<>(size);

        LinkedListQueue<Node> queue = new LinkedListQueue<>(null);
        queue.push(headNode);
        while(queue.getSize()>0){
            Node node = queue.getData(0);
            array.insert(node.data);
            if(node.leftChild != null){
                queue.push(node.leftChild);
            }
            if(node.rightChild != null){
                queue.push(node.rightChild);
            }
            queue.unshift();
        }

        return array;
    }

    public MyArray<T> inOrderTraverse() {
        MyArray<T> array = new MyArray<>(size);
        _inOrderTraverse(headNode, array);
        return array;
    }

    private void _inOrderTraverse(Node head, MyArray<T> array) {
        if(head == null || array == null){
            return;
        }
        _inOrderTraverse(head.leftChild, array);
        array.insert(head.data);
        _inOrderTraverse(head.rightChild,array);
    }

    public MyArray<T> postOrderTraverse() {
        MyArray<T> array = new MyArray<>(size);
        _postOrderTraverse(headNode, array);
        return array;
    }

    private void _postOrderTraverse(Node head, MyArray<T> array) {
        if(head == null || array == null){
            return;
        }
        _postOrderTraverse(head.leftChild, array);
        _postOrderTraverse(head.rightChild,array);
        array.insert(head.data);
    }

    public int getLevels(){
        return _getLevels(headNode);
    }

    private int _getLevels(Node node) {
        if(node == null){
            return 0;
        }
        int leftLevels = _getLevels(node.leftChild);
        int rightLevels = _getLevels(node.rightChild);
        return 1 + Math.max(rightLevels, leftLevels);
    }


    private class Node{
        public T data;
        public Node leftChild;
        public Node rightChild;
    }

    public static void main(String[] args) {
        MyArray<Integer> array = new MyArray<>(11);
        array.buildArray(new Integer[]{3,2,9,null,null,10,null,
                null,8,null,4});
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>(new LinkedList<>(array));
        System.out.print(tree.printTree("pre"));
        System.out.print(tree.printTree("in"));
        System.out.print(tree.printTree("post"));
        System.out.print(tree.printTree("breadth"));
        System.out.print("levels: " + tree.getLevels());
    }
}
