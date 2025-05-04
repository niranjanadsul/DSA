package Graphs.AdvancedDFS;

import java.util.*;

public class ArticulationPoint {
    //Also called as Tarjan's algorithm using timeIn and lowTime
    public static int timeStamp = 1;
    public List<Integer> articulationPoints(int n, List<List<Integer>> connections) {
        List<List<Integer>> adjList = generateAdjacencyList(n,connections);
        Set<Integer> articulationPoints = new TreeSet<>();
        int[] timeIn = new int[n];
        int[] low = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(visited,false);
        //multiple entry points
        for(int i=0;i<n;i++){
            if(!visited[i])
                dfs(i,-1,timeIn, low, visited, adjList,articulationPoints);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        ans.addAll(articulationPoints);
        if(ans.isEmpty())
            ans.add(-1);
        return ans;
    }

    private void dfs(int node, int parent, int[] timeIn, int[] low, boolean[] visited,
                    List<List<Integer>> adjList, Set<Integer> articulationPoints){
        visited[node]=true;
        low[node]=timeIn[node]=timeStamp;
        timeStamp++;
        int child=0;
        for(int neighbour:adjList.get(node)){
            if(neighbour==parent)
                continue;
            if(!visited[neighbour]){
                //neighbour not visited
                dfs(neighbour,node,timeIn,low,visited,adjList,articulationPoints);
                low[node] = Math.min(low[node],low[neighbour]);
                //change in condition do not check for first nodes
                if(timeIn[node]<=low[neighbour] && parent!=-1)
                    articulationPoints.add(node);
                child++;
            }else {
                //neighbour is visited
                //Changed condition
                low[node] = Math.min(low[node],timeIn[neighbour]);
            }
        }
        //if you are starting node and have more than 1 unvisited
        // child the definitely an articulation point
        if(child>1 && parent==-1){
            articulationPoints.add(node);
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
        ArticulationPoint articulationPoint = new ArticulationPoint();
        List<List<Integer>> conns = new ArrayList<>();

        for(int[] arr:new int[][]{{0,1},{1,2},{2,0},{1,3}}) {
            List<Integer> a = new ArrayList<>();
            for(int node:arr){
                a.add(node);
            }
            conns.add(a);
        }
        List<Integer> cc = articulationPoint.articulationPoints(4,conns);
        System.out.println("Done");
    }
}
