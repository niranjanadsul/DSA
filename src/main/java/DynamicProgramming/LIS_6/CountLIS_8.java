package DynamicProgramming.LIS_6;

import java.util.Arrays;

public class CountLIS_8 {
    //https://leetcode.com/problems/number-of-longest-increasing-subsequence/description/
    //TC = O(n*n)
    /*So, we need to track:
    length[i]: length of LIS ending at index i
    count[i]: number of LIS that end at i with length length[i]

    Initialization:
    Set length[i] = 1 for all i (each number alone is a LIS of length 1).
    Set count[i] = 1 for all i (initially one way to form a subsequence of that length).

    For each index i from 0 to n-1, loop through all previous indices j from 0 to i-1.
    If nums[j] < nums[i], we can extend the sequence at j to i.
    We can attach i th element ahead of jth element and the length of LIS will increase by 1
    If length[j] + 1 > length[i], we found a longer LIS for i
    So → update length[i] = length[j] + 1 and set count[i] = count[j] as this is only 1 possibility

    If length[j] + 1 == length[i], another LIS of same length → count[i] += count[j]

    Find the maximum length among all length[i].
    Sum up all count[i] where length[i] == maxLen
    */
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] length = new int[n];  // Length of LIS ending at i
        int[] count = new int[n];   // Count of LIS ending at i
        Arrays.fill(length, 1);
        Arrays.fill(count, 1);
        int maxLen = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (length[j] + 1 > length[i]) {
                        length[i] = length[j] + 1;
                        count[i] = count[j];
                    } else if (length[j] + 1 == length[i]) {
                        count[i] += count[j];
                    }
                }
            }
            maxLen = Math.max(maxLen, length[i]);
        }
        int total = 0;
        for (int i = 0; i < n; i++) {
            if (length[i] == maxLen) {
                total += count[i];
            }
        }
        return total;
    }

    public static void main(String[] args) {
        CountLIS_8 countLIS7=new CountLIS_8();
        System.out.println(countLIS7.findNumberOfLIS(new int[]{1,3,5,4,7}));//2
        System.out.println(countLIS7.findNumberOfLIS(new int[]{2,2,2,2,2}));//5
    }
}
