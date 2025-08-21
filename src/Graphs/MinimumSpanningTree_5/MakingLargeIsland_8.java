package Graphs.MinimumSpanningTree_5;

import java.util.*;

public class MakingLargeIsland_8 {
    //https://leetcode.com/problems/making-a-large-island/description/
    /*You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
    Return the size of the largest island in grid after applying this operation.
    An island is a 4-directionally connected group of 1s.

    Example 1:
    Input: grid = [[1,0],[0,1]]
    Output: 3
    Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.

    Example 2:
    Input: grid = [[1,1],[1,0]]
    Output: 4
    Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.

    Example 3:
    Input: grid = [[1,1],[1,1]]
    Output: 4
    Explanation: Can't change any 0 to 1, only one island with area = 4.*/

    int max;//if there is entire water then we can make an island os size 1 by turning 0 to 1
    public int largestIsland(int[][] grid) {
        max=Integer.MIN_VALUE;
        int n=grid.length;
        int[] par=new int[n*n];
        int[] size=new int[n*n];
        for(int i =0;i<par.length;i++){
            par[i]=-1;
            size[i]=0;
        }

        List<int[]> zeroes = new ArrayList<>();
        findIslands(grid, n, zeroes, par, size);

        if(zeroes.size()==n*n)//all zeroes
            return 1;
        for(int[] zero:zeroes){
            int x=zero[0];
            int y=zero[1];
            //for each zero simply check the sizes Ultimate parents of 4 adjacent cells and add them up
            //also add 1 of the current zero
            int[][] direction=new int[][]{
                    {1,0},{-1,0},{0,1},{0,-1}
            };
            int tempSize=1;
            Set<Integer> ultimateParentsOfAdjNodes=new HashSet<>();
            for(int[] dir:direction) {
                int row2 = x + dir[0];
                int col2 = y + dir[1];
                if (0 <= row2 && row2 < n && 0 <= col2 && col2 < n) {
                    int adjTrans = transform(row2, col2, n);
                    int UPadj = find(adjTrans, par);
                    if (UPadj != -1) {
                        ultimateParentsOfAdjNodes.add(UPadj);
                        //some neighbors can belong to same component hence add to set
                    }
                }
            }
            for(Integer ultimateParent:ultimateParentsOfAdjNodes)
                tempSize+=size[ultimateParent];
            max = Math.max(max,tempSize);
        }

        return max;

    }

    private void findIslands(int[][] grid, int n, List<int[]> zeroes, int[] par, int[] size) {
        for(int row = 0; row< n; row++){
            for(int col = 0; col< n; col++){
                if(grid[row][col]==0){
                    zeroes.add(new int[]{row,col});
                    continue;
                }
                unionIslands(n, par, size, row, col);
            }
        }
    }

    private void unionIslands(int n, int[] par, int[] size, int row, int col) {
        int trans=transform(row, col, n);
        par[trans]=trans;
        size[trans]+=1;

        int[][] direction=new int[][]{
                {1,0},{-1,0},{0,1},{0,-1}
        };
        for(int[] dir:direction) {
            int row2 = row + dir[0];
            int col2 = col + dir[1];
            if(0<=row2 && row2< n && 0<=col2 && col2< n){
                int adjTrans=transform(row2,col2, n);
                int UPadj=find(adjTrans, par);
                if(UPadj!=-1){
                    //lets do union
                    int UPtrans=find(trans, par);
                    if(UPadj!=UPtrans) {
                        //very important condition as this might be link between
                        //the two arcs of same island
                        //eventually may form a lake inside the island
                        par[UPtrans] = UPadj;
                        size[UPadj]+= size[UPtrans];
                        max=Math.max(max,size[UPadj]);
                    }
                }
            }
        }
        max=Math.max(max,size[trans]);
    }

    public int transform(int x, int y,int cols){
        return x*cols+y;
    }

    public int find(int x,int[] par){
        if(par[x]==-1)
            return -1;
        if(par[x]==x)
            return x;
        return par[x]=find(par[x],par);//path compression is very imprtant
    }

    public static void main(String[] args) {
        MakingLargeIsland_8 makingLargeIsland8=new MakingLargeIsland_8();
        System.out.println(makingLargeIsland8.largestIsland(new int[][]{
                {1,0,0,0,1,0,1,0,1},
                {0,1,0,0,1,0,0,0,0},
                {1,0,1,0,1,1,0,0,0},
                {1,1,1,1,1,0,0,0,0},
                {1,1,0,1,0,1,1,1,0},
                {0,0,0,1,0,1,0,1,1},
                {1,1,1,1,0,0,1,1,0},
                {0,1,1,1,0,1,0,0,1},
                {1,1,1,0,1,0,1,0,1}
        }));
        System.out.println(makingLargeIsland8.largestIsland(new int[][]{
                {1,1,0,1,1,1},
                {1,1,0,1,1,1},
                {1,1,0,1,1,1},
                {0,0,1,0,0,0},
                {1,1,1,1,1,1},
                {1,1,1,1,1,1}
        }));
    }
}
