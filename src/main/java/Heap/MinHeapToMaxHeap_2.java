package Heap;

public class MinHeapToMaxHeap_2 {
    //https://www.geeksforgeeks.org/problems/convert-min-heap-to-max-heap-1666385109/1
    /*You are given an array arr of N integers representing a min Heap.
    The task is to convert it to max Heap.

    A max-heap is a complete binary tree in which the value in each internal node
    is greater than or equal to the values in the children of that node.

    Example 1:

    Input:
    N = 4
    arr = [1, 2, 3, 4]
    Output:
    [4, 2, 3, 1]*/
    static void convertMinToMaxHeap(int N, int arr[]) {
        for(int i=N-1;i>=0;i--)
            maxHeapify(i,arr);
    }

    static void maxHeapify(int index, int arr[]){
        int leftChild =index*2+1;
        int rightChild = index*2+2;
        int maxIndex = index;
        if(leftChild<arr.length && arr[leftChild]>arr[maxIndex])
            maxIndex=leftChild;
        if(rightChild<arr.length && arr[rightChild]>arr[maxIndex])
            maxIndex=rightChild;
        if(index!=maxIndex) {
            int temp = arr[index];
            arr[index] = arr[maxIndex];
            arr[maxIndex] = temp;
            maxHeapify(maxIndex,arr);
        }
    }

    public static void main(String[] args) {
        convertMinToMaxHeap(4,new int[]{1,2,3,4});
    }
}
