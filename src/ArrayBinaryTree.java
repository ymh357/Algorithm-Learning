public class ArrayBinaryTree<T> {
    private MyArray<T> tree;

    public ArrayBinaryTree(MyArray<T> initialArray){
        tree = initialArray;
    }

    public MyArray<T> preOrderTraverse(){
        MyArray<T> orderedArray = new MyArray<>(tree.getCapacity());
        _preOrderTraverse(0, orderedArray);
        return orderedArray;
    }

    private void _preOrderTraverse(int index, MyArray<T> array){
        if(tree==null || index >= tree.getSize()){
            return;
        }
        if(tree.getData(index)==null){
            return;
        }
        array.insert(tree.getData(index));
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        _preOrderTraverse(leftChildIndex, array);
        _preOrderTraverse(rightChildIndex, array);
    }

    public MyArray<T> preOrderTraverse_stack(){
        MyArray<T> array = new MyArray<T>(tree.getSize());
        LinkedListStack<Integer> stack = new LinkedListStack<>(null);
        stack.push(0);
        while (stack.getSize() > 0){
            Integer index = stack.getData(stack.getSize() - 1);
            stack.pop();
            array.insert(tree.getData(index));
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            if(rightChildIndex < tree.getSize() && tree.getData(rightChildIndex) != null){
                stack.push(rightChildIndex);
            }
            if(leftChildIndex < tree.getSize() && tree.getData(leftChildIndex) != null){
                stack.push(leftChildIndex);
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
        MyArray<T> array = new MyArray<>(tree.getCapacity());
        for(T data: tree){
            if(data != null){
                array.insert(data);
            }
        }

        return array;
    }

    public MyArray<T> postOrderTraverse() {
        MyArray<T> orderedArray = new MyArray<>(tree.getCapacity());
        _postOrderTraverse(0, orderedArray);
        return orderedArray;
    }

    private void _postOrderTraverse(int index, MyArray<T> array) {
        if(tree==null || index >= tree.getSize()){
            return;
        }
        if(tree.getData(index)==null){
            return;
        }
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        _postOrderTraverse(leftChildIndex, array);
        _postOrderTraverse(rightChildIndex, array);
        array.insert(tree.getData(index));
    }

    public MyArray<T> inOrderTraverse() {
        MyArray<T> orderedArray = new MyArray<>(tree.getCapacity());
        _inOrderTraverse(0, orderedArray);
        return orderedArray;
    }

    private void _inOrderTraverse(int index, MyArray<T> array) {
        if(tree==null || index >= tree.getSize()){
            return;
        }
        if(tree.getData(index)==null){
            return;
        }
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        _inOrderTraverse(leftChildIndex, array);
        array.insert(tree.getData(index));
        _inOrderTraverse(rightChildIndex, array);
    }

    public int getLevels(){
        return _getLevels(0);
    }

    private int _getLevels(int index) {
        if(index >= tree.getSize()){
            return 0;
        }
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int leftLevels = _getLevels(leftChildIndex);
        int rightLevels = _getLevels(rightChildIndex);
        return 1 + Math.max(rightLevels, leftLevels);
    }

    public int getLeaveNum(){
        return _getLeaveNum(0);
    }

    private int _getLeaveNum(int index) {
        if(index >= tree.getSize()){
            return 0;
        }
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        if(leftChildIndex >= tree.getSize() && rightChildIndex >= tree.getSize()){
            return 1;
        }
        return _getLeaveNum(leftChildIndex) + _getLeaveNum(rightChildIndex);
    }

    public boolean isCompleteTree(){
        boolean marked = false;
        for(T data: tree){

            if(marked){
                if(data != null){
                    return false;
                }
            }

            if(data == null){
                marked = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MyArray<Integer> array = new MyArray<>(9);
        array.buildArray(new Integer[]{1,2,3,4,5,6,7});
        ArrayBinaryTree<Integer> tree = new ArrayBinaryTree<>(array);
        System.out.print(tree.printTree("pre"));
        System.out.print(tree.printTree("in"));
        System.out.print(tree.printTree("post"));
        System.out.print(tree.printTree("breadth"));
        System.out.print("levels: " + tree.getLevels());
        System.out.print("is complete: " + tree.isCompleteTree());
    }
}
