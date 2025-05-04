package Graphs.MinimumDistanceTo_0;

import java.util.LinkedList;
import java.util.Queue;

class Solution {

    public static void  main(String[] args) {
        int[][] additionalHubs = new int[][]{{0}, {1}};
        Solution solution = new Solution();
        int [][] output = solution.updateMatrix(additionalHubs);
        System.exit(1);
    }

    public int[][] updateMatrix(int[][] mat) {
        int m=mat.length;
        int n=mat[0].length;
        boolean[][] vis=new boolean[m][n];
        int[][] out=new int[m][n];
        Queue<int[]> queue=new LinkedList<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(mat[i][j]==0){
                    queue.add(new int[]{i,j});
                    vis[i][j]=true;
                }else{
                    vis[i][j]=false;
                }
            }
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                out[i][j]=Integer.MAX_VALUE;
            }
        }


        while(!queue.isEmpty()){
            int[] node=queue.poll();
            int row=node[0];
            int col=node[1];

            int up=checkUp(row-1,col,out,m,n,queue,vis);
            int down= checkDown(row+1,col,out,m,n,queue,vis);
            int left=checkLeft(row,col-1,out,m,n,queue,vis);
            int right=checkRight(row,col+1,out,m,n,queue,vis);
            if(mat[row][col]==0){
                out[row][col]=0;
            }else{
                out[row][col]=1+Math.min(Math.min(up,down),Math.min(left,right));
            }
        }

        return out;
    }



    public int checkUp(int row,int col,int[][] out,int m,int n,Queue<int[]> queue,boolean[][] vis){
        if(row>=0){
            if(!vis[row][col]){
                queue.add(new int[]{row,col});
                vis[row][col]=true;
            }
            return out[row][col];
        }
        return Integer.MAX_VALUE;
    }

    public int checkDown(int row,int col,int[][] out,int m,int n,Queue<int[]> queue,boolean[][] vis){
        if(row<m){
            if(!vis[row][col]){
                queue.add(new int[]{row,col});
                vis[row][col]=true;
            }
            return out[row][col];
        }
        return Integer.MAX_VALUE;
    }

    public int checkLeft(int row,int col,int[][] out,int m,int n,Queue<int[]> queue,boolean[][] vis){
        if(col>=0){
            if(!vis[row][col]){
                queue.add(new int[]{row,col});
                vis[row][col]=true;
            }
            return out[row][col];
        }
        return Integer.MAX_VALUE;
    }

    public int checkRight(int row,int col,int[][] out,int m,int n,Queue<int[]> queue,boolean[][] vis){
        if(col<n){
            if(!vis[row][col]){
                queue.add(new int[]{row,col});
                vis[row][col]=true;
            }
            return out[row][col];
        }
        return Integer.MAX_VALUE;
    }
}