class ArrayQueue{

    constructor(arrayLength) {
        this.arrayLength = arrayLength;
        this.myArray = new Array(this.arrayLength);
        this.headIndex = 0;
        this.tailIndex = 0;
        this.size = 0;
    }

    enqueue(insertObject){
        if (this.checkEnqueueCondition()) {
            this.myArray[this.tailIndex] = insertObject;
            this.tailIndex = (this.tailIndex + 1) % this.arrayLength;
            this.size ++;
        }
    }

    dequeue(){
        if (this.size !== 0) {
            this.myArray[this.headIndex] = null;
            this.headIndex = (this.headIndex + 1) % this.arrayLength;
            this.size --;
        }
    }

    printQueue(){
        console.log(this.myArray)
    }

    checkEnqueueCondition(){
        return this.size < this.arrayLength - 1;
    }
}


example = new ArrayQueue(4);
example.enqueue(1);
example.enqueue(2);
example.printQueue();
example.dequeue();
example.printQueue();
example.enqueue(3);
example.enqueue(4);
example.dequeue();
example.enqueue(5);
example.printQueue();



