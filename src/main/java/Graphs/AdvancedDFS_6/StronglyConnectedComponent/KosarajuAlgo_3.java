package Graphs.AdvancedDFS_6.StronglyConnectedComponent;

import java.util.*;

public class KosarajuAlgo_3 {
    //https://www.geeksforgeeks.org/problems/strongly-connected-components-kosarajus-algo/1
    /*Given an adjacency list, adj of Directed Graph,
    Find the number of strongly connected components in the graph.*/

    public int kosaraju(ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited=new boolean[adj.size()];
        Arrays.fill(visited,false);

        //STEP:1 perform DFS traversal in DIRECTED GRAPH
        // fill the stack based on finish time
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<adj.size();i++){//multi source dfs
            if(!visited[i])
                dfs(i,visited,stack,adj,true,null);
        }

        //STEP:2 Reverse the graph
        ArrayList<ArrayList<Integer>> reverseAdj = reverseGraph(adj);

        //STEP:3 perform dfs on each entry in the stack to find Strongly Connected Component
        Arrays.fill(visited,false);
        ArrayList<TreeSet<Integer>> allSCC=new ArrayList<>();//helps to print SCC
        while(!stack.isEmpty()){
            int node = stack.pop();
            if(!visited[node]){
                TreeSet<Integer> currentSCC=new TreeSet<>();
                dfs(node,visited,stack,reverseAdj,false,currentSCC);
                allSCC.add(currentSCC);
            }
        }
        return allSCC.size();
    }

    public void dfs(int node, boolean[] visited, Stack<Integer> stack,
                    ArrayList<ArrayList<Integer>> adj, boolean fillStack,TreeSet<Integer> SCC){
        visited[node] =true;
        for(int neighbour:adj.get(node)){
            if(!visited[neighbour]){
                dfs(neighbour,visited,stack,adj,fillStack,SCC);
            }
        }
        //current node is finished processing
        //add to stack
        if(fillStack)
            stack.push(node);
        if(!fillStack){
            SCC.add(node);
        }
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
        KosarajuAlgo_3 kosarajuAlgo = new KosarajuAlgo_3();
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
