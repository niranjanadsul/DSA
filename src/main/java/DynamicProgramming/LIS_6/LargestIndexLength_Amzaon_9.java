package DynamicProgramming.LIS_6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LargestIndexLength_Amzaon_9 {
    /*You have two arrays, feature1 and feature2, each of length n.
    A dataset is outlier-free if for any two indices i and j:

    If feature1[i] > feature1[j], then must have feature2[i] > feature2[j];

    If feature1[i] < feature1[j], then must have feature2[i] < feature2[j];

    If feature1[i] == feature1[j], it's not considered outlier-free.

    In other words, any selected indices must preserve the same ordering in both features,
    and ties in feature1 are disallowed.

    Goal:
    Find the largest subset of indices (not necessarily contiguous) such that
    the corresponding pairs maintain strict increasing order in both features.
    Return the size of that subset.*/

    /*Complexity
        Sorting: O(n log n)
        LIS with binary search: O(n log n)
        Overall: O(n log n) with O(n) extra space
        */
    public static int getLargestIndexLen(int[] feature1, int[] feature2) {
        int n = feature1.length;
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(feature1[i], feature2[i]);
        }

        // Sort by feature1 ascending; if tie, feature2 descending
        Arrays.sort(pairs, (a, b) -> {
            if (a.f1 != b.f1) return Integer.compare(a.f1, b.f1);
            return Integer.compare(b.f2, a.f2);
        });

        // Extract feature2 sequence
        int[] B = new int[n];
        for (int i = 0; i < n; i++) {
            B[i] = pairs[i].f2;
        }

        // Compute LIS length on B using Patience Sorting (O(n log n))
        List<Integer> lis = new ArrayList<>();
        for (int val : B) {
            int pos = Collections.binarySearch(lis, val);
            if (pos < 0) pos = -(pos + 1);
            if (pos == lis.size()) {
                lis.add(val);
            } else {
                lis.set(pos, val);
            }
        }
        return lis.size();
    }

    static class Pair {
        int f1, f2;
        Pair(int f1, int f2) { this.f1 = f1; this.f2 = f2; }
    }

    // Example usage:
    public static void main(String[] args) {
        int[] feature1 = {4, 5, 3, 1, 2};
        int[] feature2 = {2, 1, 3, 4, 5};
        System.out.println(getLargestIndexLen(feature1, feature2)); // prints 2
    }
}
