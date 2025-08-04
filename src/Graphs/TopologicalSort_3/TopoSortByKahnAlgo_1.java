package Graphs.TopologicalSort_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TopoSortByKahnAlgo_1 {
    //https://www.geeksforgeeks.org/problems/topological-sort/1
    /*
    * Given a Directed Acyclic Graph (DAG) of V (0 to V-1) vertices and
    * E edges represented as a 2D list of edges[][], where each entry
    * edges[i] = [u, v] denotes a directed edge u -> v. Return the topological sort for the given graph.

    Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that
    * for every directed edge u -> v, vertex u comes before v in the ordering.
    Note: As there are multiple Topological orders possible, you may return any of them.
    * If your returned Topological sort is correct then the output will be true else false.*/

    //Topological sort is possible only in Directed graph with no cycle (DAG)
    //Kahn's Algo is one of the approach to achieve Topological sort
    //1. traverse on all vertices and their neighbours and
    // generate array containing the indegrees of each node
    //2. While traversing the graph add the nodes with zero indegree to the queue
    //3. Same as BFS now remove a node from queue,add to output list and for this nodes neighbour reduce the indegree by 1
    //while this reduction if the indegree becomes 0 then add this node to queue
    //4. finally return the output list
    // T.C = O(V+E)
    public static ArrayList<Integer> topoSort(int V, int[][] edges) {
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

    public static ArrayList<ArrayList<Integer>> buildDirectedGraph(int nodes, int[][] edges){
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
}
