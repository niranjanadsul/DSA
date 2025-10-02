package Array;

public class TrappingRainWater_1 {
    //https://leetcode.com/problems/trapping-rain-water/
    /*Given n non-negative integers representing an elevation map where the width of each bar is 1,
    compute how much water it can trap after raining.*/
    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) return 0;

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i-1], height[i]);
        }

        rightMax[n-1] = height[n-1];
        for (int i = n-2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i+1], height[i]);
        }

        int trapped = 0;
        for (int i = 0; i < n; i++) {
            trapped += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return trapped;
    }

    public static void main(String[] args) {
        TrappingRainWater_1 solver = new TrappingRainWater_1();
        System.out.println(solver.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1})); // 6
        System.out.println(solver.trap(new int[]{4,2,0,3,2,5}));             // 9
    }
}
