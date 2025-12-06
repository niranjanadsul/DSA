package Heap;

import java.util.PriorityQueue;

public class Stream_FindMedianFromStream_14 {
    //https://leetcode.com/problems/find-median-from-data-stream/description/
    /*The median is the middle value in an ordered integer list.
    If the size of the list is even, there is no middle value,
    and the median is the mean of the two middle values.

    For example, for arr = [2,3,4], the median is 3.
    For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
    Implement the MedianFinder class:

    MedianFinder() initializes the MedianFinder object.
    void addNum(int num) adds the integer num from the data stream to the data structure.
    double findMedian() returns the median of all elements so far.
    Answers within 10-5 of the actual answer will be accepted.


    Example 1:

    Input
    ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
    [[], [1], [2], [], [3], []]
    Output
    [null, null, null, 1.5, null, 2.0]

    Explanation
    MedianFinder medianFinder = new MedianFinder();
    medianFinder.addNum(1);    // arr = [1]
    medianFinder.addNum(2);    // arr = [1, 2]
    medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
    medianFinder.addNum(3);    // arr[1, 2, 3]
    medianFinder.findMedian(); // return 2.0*/

    PriorityQueue<Integer> smallPQ;
    PriorityQueue<Integer> bigPQ;
    public Stream_FindMedianFromStream_14() {
        smallPQ = new PriorityQueue<>((a,b)->b-a);//max heap
        bigPQ=new PriorityQueue<>();//min heap
    }

    public void addNum(int num) {
        if(!bigPQ.isEmpty() && num<bigPQ.peek()){
            smallPQ.add(num);
        }else {
            bigPQ.add(num);
        }
        //adjust size
        if(bigPQ.size()-smallPQ.size()>1){
            smallPQ.add(bigPQ.poll());
        }
        if(bigPQ.size()-smallPQ.size()<0){
            bigPQ.add(smallPQ.poll());
        }
    }

    public double findMedian() {
        if(smallPQ.size()==bigPQ.size()){
            double smallMid=smallPQ.peek();
            double bigMid=bigPQ.peek();
            return (smallMid+bigMid)/(2.0);
        }else{
            return bigPQ.peek();
        }
    }


}
