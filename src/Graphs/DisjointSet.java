package Graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class DisjointSet {
    public  int removeStones(int[][] stones) {
        int maxRows=0;
        int maxCols=0;
        Queue<Node> pq=new LinkedList<>();
        for(int i=0;i<stones.length;i++){
            maxRows=Math.max(maxRows,stones[i][0]);
            maxCols=Math.max(maxCols,stones[i][1]);
        }

        int newRow=maxRows+1;
        int newCols=maxCols+1;
        int n=20002;
        int[] parent=new int[n];
        int[] size=new int[n];
        boolean[] vis=new boolean[n];

        for(int i=0;i<n;i++){
            parent[i]=i;
            size[i]=1;
            vis[i]=false;
        }

        for(int i=0;i<stones.length;i++){

            int[] arr=stones[i];

            int row=arr[0];
            int col=arr[1];
            int newCol=col+10001;
            vis[row]=true;
            vis[newCol]=true;
            unionBySize(row,newCol,parent,size);
        }

        for(int i=0;i<n;i++){
            findUParent(i,parent);
        }

        HashSet<Integer> set=new HashSet<>();
        for(int i=0;i<n;i++){
            if(vis[i]){
                set.add(parent[i]);
            }
        }

        return stones.length-set.size();
    }


    public  int findUParent(int node,int[] parent){
        if(parent[node]==node){
            return parent[node];
        }
        return parent[node]=findUParent(parent[node],parent);
    }


    public  void  unionBySize(int u,int v,int[] parent,int[] size){
        int uPS=findUParent(u,parent);
        int uPDest=findUParent(v,parent);
        if(uPS==uPDest){
            return ;
        }
        if(size[uPS]<size[uPDest]){
            parent[uPS]=uPDest;
            size[uPDest]=size[uPDest]+size[uPS];
        }else{
            parent[uPDest]=uPS;
            size[uPS]=size[uPDest]+size[uPS];
        }

    }

    public static void main(String[] a){
        int n = 6;
        int[][] stones = {
                {1,2}, {0, 1},
                {7, 3}, {5, 5},
                {7, 1}, {6, 1},{0,6},{5,1},{4,2},{8,4}
        };

        DisjointSet obj = new DisjointSet();
        obj.removeStones(stones);
    }
}
