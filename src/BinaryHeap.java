public class BinaryHeap<T extends Comparable<T>> extends ArrayBinaryTree<T>  {

    private final boolean minHeap;
    public BinaryHeap(MyArray<T> initialArray, boolean type) {
        super(initialArray);
        minHeap = type;
    }

    public boolean isValid(){

        return isCompleteTree() && _isValid(0, minHeap);
    }

    private boolean _isValid(int index, boolean isMinType){
        if(index >= tree.getSize()){
            return false;
        }
        int leftChildIndex = index * 2 + 1;
        int rightChildIndex = index * 2 + 2;

        if((leftChildIndex < tree.getSize() && tree.getData(leftChildIndex) != null)
                || (rightChildIndex < tree.getSize() && tree.getData(rightChildIndex) != null)){
            if(leftChildIndex < tree.getSize() && tree.getData(leftChildIndex) != null){
                if((tree.getData(index).compareTo(tree.getData(leftChildIndex)) > 0 && isMinType)
                        || tree.getData(index).compareTo(tree.getData(leftChildIndex)) < 0 && !isMinType){
                    return false;
                }
            }
            if(rightChildIndex < tree.getSize() && tree.getData(rightChildIndex) != null){
                if((tree.getData(index).compareTo(tree.getData(rightChildIndex)) > 0 && isMinType)
                        || tree.getData(index).compareTo(tree.getData(rightChildIndex)) < 0 && !isMinType){
                    return false;
                }
            }
            return _isValid(leftChildIndex, isMinType) && _isValid(rightChildIndex, isMinType);
        }else {
            return true;
        }
    }

    private void upAdjust(int index){
        int parentIndex = getParent(index);
        while (parentIndex >= 0){
            if((tree.getData(parentIndex).compareTo(tree.getData(index)) > 0 && minHeap)
                || tree.getData(parentIndex).compareTo(tree.getData(index)) < 0 && !minHeap){

                T tmp = tree.getData(index);
                tree.update(index, tree.getData(parentIndex));
                tree.update(parentIndex, tmp);

                index = parentIndex;
                parentIndex = getParent(index);
                continue;
            }
            break;
        }
    }

    private void downAdjust(int index){
        int leftChildIndex = 2 * index + 1;
        while (leftChildIndex < tree.getSize()){
            int rightChildIndex = leftChildIndex == index - 1 ? leftChildIndex + 1 : -1;
            T rightData = rightChildIndex == -1 ? null : tree.getData(rightChildIndex);
            T leftData = tree.getData(leftChildIndex);
            int childIndex = leftData != null ? leftChildIndex : rightData != null ? rightChildIndex : -1;
            if(childIndex == -1){
                break;
            }
            if((tree.getData(childIndex).compareTo(tree.getData(index)) < 0 && minHeap)
                    || (tree.getData(childIndex).compareTo(tree.getData(index)) > 0 && !minHeap)){
                T tmp = tree.getData(index);
                tree.update(index, tree.getData(childIndex));
                tree.update(childIndex, tmp);

                index = childIndex;
                leftChildIndex = 2 * index + 1;
                continue;
            }
            break;
        }
    }

    public void addData(T data){
        int position = getSize();
        if(getSize() < tree.getSize()){
            tree.update(position, data);
        }else{
            tree.insert(data);
        }
        upAdjust(position);
    }

    public void deleteRoot(){
        tree.update(0, tree.getData(getSize() - 1));
        tree.delete();
        downAdjust(0);
    }

    public void selfBuild(){
        LinkedListStack<Integer> stack = new LinkedListStack<Integer>(null);
        for(int i=0;i<tree.getSize();i++){
            T ele = tree.getData(i);
            if(ele != null){
                int leftChildIndex = 2 * i + 1;
                int rightChildIndex =leftChildIndex + 1;
                if((leftChildIndex < tree.getSize() && tree.getData(leftChildIndex) != null)
                        || (rightChildIndex < tree.getSize() && tree.getData(rightChildIndex) != null)){
                    stack.push(i);
                }
            }
        }

        while (stack.getSize() > 0){
            int index = stack.getData(stack.getSize() - 1);
            downAdjust(index);
            stack.pop();
        }
    }

    public static void main(String[] args) {
        MyArray<Integer> array = new MyArray<>(9);
        array.buildArray(new Integer[]{6,3,8,2,4,7,9,1,null,null,null,null,null,null,null});
        BinaryHeap<Integer> tree = new BinaryHeap<>(array, true);
        System.out.print(tree.printTree("pre"));
        System.out.print(tree.printTree("in"));
        System.out.print(tree.printTree("post"));
        System.out.print(tree.printTree("breadth"));
        System.out.println("levels: " + tree.getLevels());
        System.out.println("is complete: " + tree.isCompleteTree());
        System.out.println("is valid: " + tree.isValid());
        tree.selfBuild();
        tree.addData(0);
        System.out.print(tree.displayTree());
        tree.deleteRoot();
        System.out.print(tree.displayTree());
        System.out.println("is valid: " + tree.isValid());
        //tree.downAdjust(3);
        //System.out.print(tree.printTree("breadth"));
    }
}
