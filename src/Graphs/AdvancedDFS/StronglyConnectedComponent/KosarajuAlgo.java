package Graphs.AdvancedDFS.StronglyConnectedComponent;

import Graphs.AdvancedDFS.ArticulationPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class KosarajuAlgo {

    public int kosaraju(ArrayList<ArrayList<Integer>> adj) {
        int scc = 0;
        boolean[] visited=new boolean[adj.size()];
        Arrays.fill(visited,false);

        //STEP:1 perform DFS traversal in DIRECTED GRAPH
        // and sort the nodes based on finish time
        Stack<Integer> sortedNodes = new Stack<>();
        for(int i=0;i<adj.size();i++){
            if(!visited[i])
                dfs(i,visited,sortedNodes,adj);
        }

        //STEP:2 Reverse the graph
        ArrayList<ArrayList<Integer>> reverseAdj = reverseGraph(adj);

        //STEP:3 perform dfs on each entry in the stack to find Strongly Connected Component
        Arrays.fill(visited,false);
        while(!sortedNodes.isEmpty()){
            int node = sortedNodes.pop();
            if(!visited[node]){
                dfs(node,visited,new Stack<>(),reverseAdj);
                scc++;
            }
        }
        return scc;
    }

    public void dfs(int node, boolean[] visited, Stack<Integer> sortedNodes,
                    ArrayList<ArrayList<Integer>> adj){
        visited[node] =true;
        for(int neighbour:adj.get(node)){
            if(!visited[neighbour]){
                dfs(neighbour,visited,sortedNodes,adj);
            }
        }
        sortedNodes.push(node);
    }

    public ArrayList<ArrayList<Integer>> reverseGraph(ArrayList<ArrayList<Integer>> adj){
        ArrayList<ArrayList<Integer>> reverseAdj = new ArrayList<>();
        for(int i=0; i<adj.size();i++){
            reverseAdj.add(i,new ArrayList<>());
        }
        for(int i=0; i<adj.size();i++){
            for(int j:adj.get(i)){
                reverseAdj.get(j).add(i);
            }
        }
        return reverseAdj;
    }

    public ArrayList<ArrayList<Integer>>  generateDirectedAdjacencyList(int n, List<List<Integer>> connections){
        int i=0;
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        while(i<n){
            adjList.add(new ArrayList<>());
            i++;
        }
        for(List<Integer> neighbours:connections){
            adjList.get(neighbours.get(0)).add(neighbours.get(1));
        }
        return adjList;
    }

    public static void main(String[] arg){
        KosarajuAlgo kosarajuAlgo = new KosarajuAlgo();
        List<List<Integer>> conns = new ArrayList<>();

        for(int[] arr:new int[][]{{1,0},{0,2},{2,1},{0,3},{3,4}}) {
            List<Integer> a = new ArrayList<>();
            for(int node:arr){
                a.add(node);
            }
            conns.add(a);
        }
        ArrayList<ArrayList<Integer>> adj = kosarajuAlgo.generateDirectedAdjacencyList(5,conns);
        kosarajuAlgo.kosaraju(adj);
        System.out.println("Done");
    }
}
