package Graphs.AdvancedDFS_6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CriticalConnectionInNetwork_1 {
    //Also called as Tarjan's algorithm using timeIn and lowTime
    public static int timeStamp = 1;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> adjList = generateAdjacencyList(n,connections);
        List<List<Integer>> criticalConns = new ArrayList<>();
        int[] timeIn = new int[n];
        int[] low = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(visited,false);
        dfs(0,-1,timeIn, low, visited, adjList,criticalConns);
        return criticalConns;
    }

    private void dfs(int node, int parent, int[] timeIn, int[] low, boolean[] visited,
                    List<List<Integer>> adjList, List<List<Integer>> criticalConns){
        visited[node]=true;
        low[node]=timeIn[node]=timeStamp;
        timeStamp++;
        for(int neighbour:adjList.get(node)){
            if(neighbour==parent)
                continue;
            if(!visited[neighbour]){
                //neighbour not visited
                dfs(neighbour,node,timeIn,low,visited,adjList,criticalConns);
                low[node] = Math.min(low[node],low[neighbour]);
                if(timeIn[node]<low[neighbour]) {
                    //if the current nodes in time is lower than the low of neighbour
                    //this indicates that the current node has to be visited first
                    //and then the neighbour
                    //hence the edge between these nodes is no cyclic and is critical
                    criticalConns.add(Arrays.asList(node, neighbour));
                }
            }else {
                //neighbour is visited
                low[node] = Math.min(low[node],low[neighbour]);
            }
        }
    }

    private List<List<Integer>> generateAdjacencyList(int n, List<List<Integer>> connections){
        int i=0;
        List<List<Integer>> adjList = new ArrayList<>();
        while(i<n){
            adjList.add(new ArrayList<>());
            i++;
        }
        for(List<Integer> neighbours:connections){
            adjList.get(neighbours.get(0)).add(neighbours.get(1));
            adjList.get(neighbours.get(1)).add(neighbours.get(0));
        }
        return adjList;
    }

    public static void main(String[] arg){
        CriticalConnectionInNetwork_1 criticalConnection = new CriticalConnectionInNetwork_1();
        List<List<Integer>> conns = new ArrayList<>();
        for(int[] arr:new int[][]{{0,1},{1,2},{2,0},{1,3}}) {
            List<Integer> a = new ArrayList<>();
            for(int node:arr){
                a.add(node);
            }
            conns.add(a);
        }
        List<List<Integer>> cc = criticalConnection.criticalConnections(4,conns);
        System.out.println("Done");
    }
}
