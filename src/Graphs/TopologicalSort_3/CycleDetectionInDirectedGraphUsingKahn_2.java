package Graphs.TopologicalSort_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CycleDetectionInDirectedGraphUsingKahn_2 {
    //https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
    /*Given a Directed Graph with V vertices (Numbered from 0 to V-1) and E edges,
    check whether it contains any cycle or not.
    The graph is represented as a 2D vector edges[][],
    where each entry edges[i] = [u, v] denotes an edge from vertex u to v.*/

    //TC=O(V+E)
    //we can use Kahn's algo
    //topo sort can be performed only on DAG
    //After topo sort on DAG, array of V vertices is generated
    //if the topo sort does not produce V nodes then Cycle present
    public boolean isCyclic(int V, int[][] edges) {
        return topoSort(V,edges).size()!=V;
    }

    public ArrayList<Integer> topoSort(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjLs=buildDirectedGraph(V,edges);
        int[] indegree = new int[V];
        Arrays.fill(indegree,0);

        for(int i=0;i<V;i++){
            for(int neighbour:adjLs.get(i)){
                indegree[neighbour]+=1;
            }
        }

        Queue<Integer> queue=new LinkedList<>();
        for(int i=0;i<V;i++){
            if(indegree[i]==0)
                queue.add(i);
        }

        int count=0;
        ArrayList<Integer> topoSort=new ArrayList<>();
        while (!queue.isEmpty()){
            int node=queue.remove();
            topoSort.add(node);
            count++;
            for(int neighbour:adjLs.get(node)){
                indegree[neighbour]-=1;
                if(indegree[neighbour]==0)
                    queue.add(neighbour);
            }
        }
        return topoSort;
    }

    public ArrayList<ArrayList<Integer>> buildDirectedGraph(int nodes, int[][] edges){
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for(int i =0;i<nodes;i++){
            adjList.add(i,new ArrayList<>());
        }
        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];
            adjList.get(u).add(v);
        }
        return adjList;
    }

    public static void main(String[] args) {
        CycleDetectionInDirectedGraphUsingKahn_2 detectionInDirectedGraph4=new CycleDetectionInDirectedGraphUsingKahn_2();
    }
}
