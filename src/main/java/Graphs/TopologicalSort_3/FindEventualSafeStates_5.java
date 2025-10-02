package Graphs.TopologicalSort_3;

import java.util.*;

public class FindEventualSafeStates_5 {
    //https://leetcode.com/problems/find-eventual-safe-states/description/
    /*
    * There is a directed graph of n nodes with each node labeled from 0 to n - 1.
    * The graph is represented by a 0-indexed 2D integer array graph where graph[i]
    * is an integer array of nodes adjacent to node i, meaning there is an edge from node i to
    * each node in graph[i].
    A node is a terminal node if there are no outgoing edges. A node is a safe node
    * if every possible path starting from that node leads to a terminal node (or another safe node).
    Return an array containing all the safe nodes of the graph.
    * The answer should be sorted in ascending order.*/

    //this is same as topo sort after converting the graph to parent graph
    //TC=O(V+E) +O(V+E)
    //generating the parent graph annd then topo sort
    public List<Integer> eventualSafeNodes(int[][] graph) {
        //build the parent graph
        ArrayList<ArrayList<Integer>> parentGraph = new ArrayList<>();
        for(int i=0;i<graph.length;i++){
            parentGraph.add(i,new ArrayList<>());
        }
        for(int i=0;i<graph.length;i++){
            for(int j=0;j<graph[i].length;j++){
                parentGraph.get(graph[i][j]).add(i);
            }
        }

        return new ArrayList<>(topoSort(parentGraph));
    }

    public TreeSet<Integer> topoSort(ArrayList<ArrayList<Integer>> adjLs) {
        int V=adjLs.size();
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

        TreeSet<Integer> topoSort=new TreeSet<>();
        while (!queue.isEmpty()){
            int node=queue.remove();
            topoSort.add(node);
            for(int neighbour:adjLs.get(node)){
                indegree[neighbour]-=1;
                if(indegree[neighbour]==0)
                    queue.add(neighbour);
            }
        }
        return topoSort;
    }

    public static void main(String[] args) {
        FindEventualSafeStates_5 findEventualSafeStates5=new FindEventualSafeStates_5();
        System.out.println(findEventualSafeStates5.eventualSafeNodes(new int[][]{
                {1,2},{2,3},{5},{0},{5},{},{}
        }));
    }
}
