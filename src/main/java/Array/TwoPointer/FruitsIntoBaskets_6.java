package Array.TwoPointer;

import java.util.HashMap;

public class FruitsIntoBaskets_6 {
    //https://leetcode.com/problems/fruit-into-baskets/description/
    /*You are visiting a farm that has a single row of fruit trees arranged from left to right.
    The trees are represented by an integer array fruits where fruits[i] is the type of fruit the
    ith tree produces.

    You want to collect as much fruit as possible.
    However, the owner has some strict rules that you must follow:

    You only have two baskets, and each basket can only hold a single type of fruit.
    There is no limit on the amount of fruit each basket can hold.
    Starting from any tree of your choice, you must pick exactly one fruit from every tree
    (including the start tree) while moving to the right. The picked fruits must fit in one of
    your baskets.
    Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
    Given the integer array fruits, return the maximum number of fruits you can pick.
    Example 1:

    Input: fruits = [1,2,1]
    Output: 3
    Explanation: We can pick from all 3 trees.
    Example 2:

    Input: fruits = [0,1,2,2]
    Output: 3
    Explanation: We can pick from trees [1,2,2].
    If we had started at the first tree, we would only pick from trees [0,1].
    Example 3:

    Input: fruits = [1,2,3,2,2]
    Output: 4
    Explanation: We can pick from trees [2,3,2,2].
    If we had started at the first tree, we would only pick from trees [1,2].*/

    public int totalFruit(int[] fruits) {
        int i=0,j=0,count=0,maxCount=0;
        HashMap<Integer,Integer> map=new HashMap<>();
        while(j<fruits.length){
            if(map.containsKey(fruits[j])){
                map.compute(fruits[j], (k, c) -> c + 1);
                count++;
                j++;
            }else{
                //map does not contain fruit[j] type of fruit
                //check if the map/basket is available
                //if basket is available then simply use the basket
                if(map.size()<2){
                    map.put(fruits[j],1);
                    count++;
                    j++;
                }else{
                    //baskets are full and we need to remove some fruits to make
                    // the basket available for this new type of fruit
                    //this is the restore phase in this two pointer approach using the i pointer
                    while(map.size()>=2){
                        int c = map.get(fruits[i]);
                        c=c-1;
                        map.put(fruits[i],c);
                        if(map.get(fruits[i])==0)
                            map.remove(fruits[i]);
                        count--;
                        i++;
                    }
                }
            }
            maxCount=Math.max(maxCount,count);
        }
        return maxCount;
    }

    public static void main(String[] args) {
        FruitsIntoBaskets_6 fruitsIntoBaskets6=new FruitsIntoBaskets_6();
        fruitsIntoBaskets6.totalFruit(new int[]{3,3,3,1,2,1,1,2,3,3,4});
    }
}
