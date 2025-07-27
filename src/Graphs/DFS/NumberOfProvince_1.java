package Graphs.DFS;

import java.util.Arrays;

public class NumberOfProvince_1 {
    //https://leetcode.com/problems/number-of-provinces/description/
    /*There are n cities. Some of them are connected, while some are not.
    If city a is connected directly with city b, and city b is connected directly with city c,
    then city a is connected indirectly with city c.
    A province is a group of directly or indirectly connected cities and
    no other cities outside of the group.
    You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and
    the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
    Return the total number of provinces.*/

    //we will iterate on each vertex and if it is unvisited we will call DFS traversal and this
    //node will belong new province/connected component
    //TC = O(V*V) //as we visit each cell in the matrix
    public int findCircleNum(int[][] isConnected) {
        int[] visited = new int[isConnected.length];
        Arrays.fill(visited,0);
        int count=0;
        for(int v=0;v<visited.length;v++){
            if(visited[v]!=1){
                dfs(v,isConnected,visited);
                count++;
            }
        }
        return count;
    }

    public void dfs(int vertex,int[][] connected, int[] visited){
        if(vertex>=visited.length || visited[vertex]==1)
            return;
        visited[vertex]=1;
        for(int i=0;i<connected.length;i++){
            if(connected[vertex][i]==1)
                dfs(i,connected,visited);
        }
    }
}
