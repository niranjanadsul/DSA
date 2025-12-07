package GreedyAlgorithm;

import java.util.*;

public class FractionalKnapsack_1 {
    //https://www.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1
    /*Given two arrays, val[] and wt[] , representing the values and weights of items,
    and an integer capacity representing the maximum weight a knapsack can hold,
    determine the maximum total value that can be achieved by putting items in the knapsack.
     You are allowed to break items into fractions if necessary.
    Return the maximum value as a double, rounded to 6 decimal places.

    Examples :

    Input: val[] = [60, 100, 120], wt[] = [10, 20, 30], capacity = 50
    Output: 240.000000
    Explanation: By taking items of weight 10 and 20 kg and 2/3 fraction of 30 kg.
    Hence total price will be 60+100+(2/3)(120) = 240
    Input: val[] = [500], wt[] = [30], capacity = 10
    Output: 166.670000
    Explanation: Since the item’s weight exceeds capacity, we take a fraction 10/30 of it,
    yielding value 166.670000.*/
    public double fractionalKnapsack(int[] val, int[] wt, int capacity) {
        ArrayList<double[]> pq = new ArrayList<>();
        for(int i=0;i<val.length;i++){
            pq.add(new double[]{wt[i],val[i]});
        }
        pq.sort(Comparator.comparingDouble(a -> a[1]/a[0])); //ascending sort based on
        // value of unit weight
        List<double[]> reverseLS = pq.reversed();//descending order
        double ans=0;
        while(capacity>0 && !reverseLS.isEmpty()){
            double[] item=reverseLS.removeFirst();
            if(item[0]<=capacity){
                capacity-=item[0];
                ans+=item[1];
            }else {
                ans+=(capacity*item[1]/item[0]);
                capacity=0;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        FractionalKnapsack_1 fractionalKnapsack1=new FractionalKnapsack_1();
        System.out.println(fractionalKnapsack1.fractionalKnapsack(new int[]{8,2,10, 1,9, 7, 2, 6, 4, 9},
                new int[]{10, 1 ,7 ,7, 5, 1, 8, 6, 8, 7},21 ));//37
        System.out.println(fractionalKnapsack1.fractionalKnapsack(new int[]{60, 100, 120},
                new int[]{10,20,30},50));//240
    }
}
