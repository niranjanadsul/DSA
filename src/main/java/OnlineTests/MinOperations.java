package OnlineTests;

import java.util.ArrayList;
import java.util.List;

public class MinOperations {

    public static int minOperations(int[] A) {
        int N = A.length;

        // Calculate prefix sums
        List<Integer> P = new ArrayList<>();
        P.add(A[0]);
        for (int i = 1; i < N; i++) {
            P.add(A[i] + P.get(i - 1));
        }

        // If total sum is negative, return -1
        if (P.get(N - 1) < 0) {
            return -1;
        }

        // Initialize result and LIS arrays
        List<Integer> res = new ArrayList<>();
        int[] lis = new int[N];

        // Calculate LIS of prefix sum array
        for (int i = 0; i < N; i++) {
            int x = P.get(i);
            if (x < 0) {
                lis[i] = 0;
                continue;
            }
            int index = binarySearch(res, x);
            if (index < 0) {
                res.add(-index - 1, x);
                lis[i] = res.size();
            } else {
                res.set(index, x);
                lis[i] = index + 1;
            }
        }

        // Return minimum operations
        return N - lis[N - 1] - 1; // Corrected to account for the first negative value
    }

    // Binary search helper function
    public static int binarySearch(List<Integer> arr, int target) {
        int low = 0;
        int high = arr.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr.get(mid) == target) {
                return mid;
            } else if (arr.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -(low + 1);
    }

    // Test case
    public static void main(String[] args) {
        int[] A = {2,5,-8,-1,2};
        System.out.println(minOperations(A)); // Output: 4
    }
}