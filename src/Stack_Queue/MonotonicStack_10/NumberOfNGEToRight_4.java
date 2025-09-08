package Stack_Queue.MonotonicStack_10;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NumberOfNGEToRight_4 {
    //https://www.geeksforgeeks.org/problems/number-of-nges-to-the-right/1
    /*Given an array of arr[] and Q queries of indices.
    For each query indices[i], determine the count of elements in arr that are strictly greater
    than arr[indices[i]] to its right (after the position indices[i]).
    Examples :
    Input: arr[] = [3, 4, 2, 7, 5, 8, 10, 6], queries = 2, indices[] = [0, 5]
    Output:  [6, 1]
    Explanation: The next greater elements to the right of 3(index 0) are 4,7,5,8,10,6.
     The next greater elements to the right of 8(index 5) is only 10.

    Input: arr[] = [1, 2, 3, 4, 1], queries = 2, indices[] = [0, 3]
    Output:  [3, 0]
    Explanation: The count of numbers to the right of index 0 which are greater
    than arr[0] is 3 i.e. (2, 3, 4). Similarly, the count of numbers to the right of index 3 which are
    greater than arr[3] is 0, since there are no greater elements than 4 to the right of the array.*/


    //we will simply iterate on the query indexes
    //for each query index we will iterate to the right of the array and count the greater elements
    //T.C. = O(number of queries * N)
    public static int[] count_NGE(int nums2[], int indices[]) {
        int[] count=new int[indices.length];
        for(int i=0;i<indices.length;i++){
            int c=0;
            int index=indices[i];
            for(int j=index+1;j<nums2.length;j++){
                if(nums2[index]<nums2[j])
                    c++;
            }
            count[i]=c;
        }
        return count;
    }

    public static void main(String[] args) {
        NumberOfNGEToRight_4.count_NGE(new int[]{3, 4, 2, 7, 5, 8, 10, 6},new int[]{0,5});
    }
}
