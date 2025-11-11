package Heap;

public class ValidateMaxHeap_1 {
    //https://www.geeksforgeeks.org/problems/does-array-represent-heap4345/1
    /*Given an array arr of size n,
    the task is to check if the given array can be a
    level order representation of a Max Heap.
    Example 1:

    Input:
    n = 6
    arr[] = {90, 15, 10, 7, 12, 2}
    Output:
    1
    Explanation:
    The given array represents below tree
           90
         /    \
       15      10
      /  \     /
    7    12  2
    The tree follows max-heap property as every
    node is greater than all of its descendants.*/
    public boolean countSub(long arr[], long n) {
        for(int i=0;i<n;i++){
            int leftChild = i*2+1;
            int rightChild = i*2+2;
            if(leftChild<n && arr[i]<arr[leftChild])
                return false;
            if(rightChild<n && arr[i]<arr[rightChild])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        ValidateMaxHeap_1 validateMaxHeap1=new ValidateMaxHeap_1();
        long[] arr=new long[]{90, 15, 10, 7, 12, 2};
        System.out.println(validateMaxHeap1.countSub(arr,6));
        arr=new long[]{9, 15, 10, 7, 12, 11};
        System.out.println(validateMaxHeap1.countSub(arr,6));
    }
}
