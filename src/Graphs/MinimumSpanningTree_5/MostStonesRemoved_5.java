package Graphs.MinimumSpanningTree_5;

import java.util.*;

public class MostStonesRemoved_5 {
    //https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
    /*On a 2D plane, we place n stones at some integer coordinate points.
    Each coordinate point may have at most one stone.
        A stone can be removed if it shares either the same row or the same column as another stone
        that has not been removed.
        Given an array stones of length n where stones[i] = [xi, yi] represents the
        location of the ith stone, return the largest possible number of stones that can be removed.

        Example 1:
        Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
        Output: 5
        Explanation: One way to remove 5 stones is as follows:
        1. Remove stone [2,2] because it shares the same row as [2,1].
        2. Remove stone [2,1] because it shares the same column as [0,1].
        3. Remove stone [1,2] because it shares the same row as [1,0].
        4. Remove stone [1,0] because it shares the same column as [0,0].
        5. Remove stone [0,1] because it shares the same row as [0,0].
        Stone [0,0] cannot be removed since it does not share a row/column with another stone still
        on the plane.

        Example 2:
        Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
        Output: 3
        Explanation: One way to make 3 moves is as follows:
        1. Remove stone [2,2] because it shares the same row as [2,0].
        2. Remove stone [2,0] because it shares the same column as [0,0].
        3. Remove stone [0,2] because it shares the same row as [0,0].
        Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with
        another stone still on the plane.

        Example 3:
        Input: stones = [[0,0]]
        Output: 0
        Explanation: [0,0] is the only stone on the plane, so you cannot remove it.*/
    //0<= x,y<10000     i.e. each x and y can have 10001 values
    int component=0;
    public int removeStones(int[][] stones) {
       int[] par= new int[20002];
       Arrays.fill(par,-1);
       for(int[] edge:stones){//treat a stone as an edge
           int u=edge[0];
           int v=edge[1]+10001;
           union(u,v,par);
       }
       return stones.length-component;
    }

    public int find(int x, int[] par) {
        if(par[x]==-1) {
            component++;//as this node x is  unvisited so we found a new component
            return par[x] = x;
        }
        if(par[x]==x)
            return x;
        return par[x]=find(par[x],par);
    }

    public void union(int x, int y, int[] par) {
        int UPx = find(x,par);
        int UPy = find(y,par);
        if (UPx != UPy) {
            par[UPy]=UPx;
            component--;//merge lead to reduction in components
        }
    }

    public static void main(String[] args) {
        MostStonesRemoved_5 mostStonesRemoved5=new MostStonesRemoved_5();
        System.out.println(mostStonesRemoved5.removeStones(new int[][]{
                {0,0},{0,1},{1,0},{1,2},{2,1},{2,2}
        }));
    }
}
