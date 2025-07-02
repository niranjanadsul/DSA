package DynamicProgramming.DP_1D;

public class FrogJump_2 {
    //https://www.geeksforgeeks.org/problems/geek-jump/1
    /*Given an integer array height[] where height[i] represents the height of the i-th stair,
    a frog starts from the first stair and wants to reach the top. From any stair i,
    the frog has two options: it can either jump to the (i+1)th stair or the (i+2)th stair.
    The cost of a jump is the absolute difference in height between the two stairs.
    Determine the minimum total cost required for the frog to reach the top.*/
    // to reach 0 index frog need 0 cost
    // to reach i th index a frog can jump from i-1 or i-2 th index based on which jump is min cost
    int minCost(int[] height) {
        // code here
        int[] cost = new int[height.length];
        cost[0] = 0;
        if(height.length>=2)
            cost[1] = Math.abs(height[0]-height[1]);
        int i=2;
        while(i<height.length){
            cost[i]=Math.min(cost[i-1]+Math.abs(height[i-1]-height[i]),
                    cost[i-2]+Math.abs(height[i-2]-height[i]));
            i++;
        }
        return cost[cost.length-1];
    }

    int minCost(int[] height, int k) {
        // code here
        int[] cost = new int[height.length];
        cost[0] = 0;
        int i=1;
        while(i<height.length){
            int j=1;
            int min = Integer.MAX_VALUE;
            while(i-j>=0 && j<=k){
                min = Math.min(min,cost[i-j]+Math.abs(height[i-j]-height[i]));
                j++;
            }
            cost[i]=min;
            i++;
        }
        return cost[cost.length-1];
    }

    public static void main(String[] args) {
        FrogJump_2 frogJump2= new FrogJump_2();
        int cost = frogJump2.minCost(new int[]{20, 30, 40, 20},2);//20
        System.out.println(cost);
        cost = frogJump2.minCost(new int[]{30, 20, 50, 10, 40},2);//30
        System.out.println(cost);
        cost = frogJump2.minCost(new int[]{10, 5, 20, 0, 15},2);//15
        System.out.println(cost);
        cost = frogJump2.minCost(new int[]{15, 4, 1, 14, 15},3);//2
        System.out.println(cost);
    }
}
