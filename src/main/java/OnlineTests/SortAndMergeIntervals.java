package OnlineTests;
// Java Code to Merge Overlapping Intervals by checking
// overlapping intervals only

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SortAndMergeIntervals {

    static List<List<Integer>> mergeOverlap(int[][] arr) {
        // Sort intervals based on start values
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> res = new ArrayList<>();
        res.add(new int[]{arr[0][0], arr[0][1]});

        for (int i = 1; i < arr.length; i++) {
            int[] last = res.get(res.size() - 1);
            int[] curr = arr[i];

            // If current interval overlaps with the last merged
            // interval, merge them
            if (curr[0] <= last[1])
                last[1] = Math.max(last[1], curr[1]);
            else
                res.add(new int[]{curr[0], curr[1]});
        }

        List<List<Integer>> arr1 = new ArrayList<>();
        for(int i =0;i<res.size();i++){
            List<Integer> innerl = new ArrayList<>();
            innerl.add(res.get(i)[0]);
            innerl.add(res.get(i)[1]);
            arr1.add(innerl);
        }
        return arr1;
    }

    public static void main(String[] args) {
        int[][] arr = {{7, 7}, {2, 3}, {6, 11}, {1, 2}};
        List<List<Integer>> res = mergeOverlap(arr);

        for (List<Integer> interval : res)
            System.out.println(interval.get(0) + " " + interval.get(1));
    }
}
