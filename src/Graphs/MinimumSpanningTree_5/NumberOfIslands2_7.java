package Graphs.MinimumSpanningTree_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberOfIslands2_7 {
    //https://www.geeksforgeeks.org/problems/number-of-islands/1
    /*You are given a n,m which means the row and column of the 2D matrix and
    an array of  size k denoting the number of operations.
    Matrix elements is 0 if there is water or 1 if there is land. Originally,
    the 2D matrix is all 0 which means there is no land in the matrix.
    The array has k operator(s) and each operator has two integer A[i][0], A[i][1] means
    that you can change the cell matrix[A[i][0]][A[i][1]] from sea to island.
    Return how many island are there in the matrix after each operation.
    You need to return an array of size k.

        Note : An island means group of 1s such that they share a common side.
        Example 1:
        Input: n = 4
        m = 5
        k = 4
        A = {{1,1},{0,1},{3,3},{3,4}}

        Output: 1 1 2 2
        Explanation:
        0.  00000
            00000
            00000
            00000
        1.  00000
            01000
            00000
            00000
        2.  01000
            01000
            00000
            00000
        3.  01000
            01000
            00000
            00010
        4.  01000
            01000
            00000
            00011*/

    int components=0;
    public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {
        int[] par = new int[rows*cols];
        Arrays.fill(par,-1);
        List<Integer> ans=new ArrayList<>();
        for(int[] edge:operators){
            int x=edge[0];
            int y=edge[1];
            int trans=transform(x,y,cols);
            if(par[trans]!=-1){
                //already processed
                ans.add(components);
                continue;
            }
            par[trans]=trans;//new island found
            components++;

            int[][] direction=new int[][]{
                    {1,0},{-1,0},{0,1},{0,-1}
            };
            for(int[] dir:direction){
                int row=x+dir[0];
                int col=y+dir[1];
                if(0<=row && row<rows && 0<=col && col<cols){
                    int adjTrans=transform(row,col,cols);
                    int UPadj=find(adjTrans,par);
                    if(UPadj!=-1){
                        int UPtrans=find(trans,par);
                        if(UPadj!=UPtrans) {
                            //very important condition as this might be link between
                            //the two arcs of same island
                            //eventually may form a lake inside the island
                            par[UPtrans] = UPadj;
                            components--;
                        }
                    }
                }
            }
            ans.add(components);
        }
        return ans;
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
        NumberOfIslands2_7 numberOfIslands7=new NumberOfIslands2_7();
        /*System.out.println(numberOfIslands7.numOfIslands(5,8,new int[][]{
                {0,3},{4,3},{4,4},{3,5},{4,5}
        }));//1,2,2,3,2*/
        System.out.println(numberOfIslands7.numOfIslands(2,4,new int[][]{
                {1, 3}, {0 ,3},
                {0 ,1},
                        {1, 1},
                                {1 ,0},
                                        {1, 2},
                                                {0 ,3},
                                                        {1 ,2}
        }));

    }
}
