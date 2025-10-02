package Graphs.ShortestPath_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInDAG_2 {
    /*Given a Directed Acyclic Graph of V vertices from 0 to n-1 and a 2D Integer array(or vector)
    edges[ ][ ] of length E, where there is a directed edge from edge[i][0] to edge[i][1]
    with a distance of edge[i][2] for all i.

    Find the shortest path from src(0) vertex to all the vertices and
     if it is impossible to reach any vertex, then return -1 for that vertex.

    Examples :

    Input: V = 4, E = 2, edges = [[0,1,2], [0,2,1]]
    Output: [0, 2, 1, -1]
    Explanation: Shortest path from 0 to 1 is 0->1 with edge weight 2.
    Shortest path from 0 to 2 is 0->2 with edge weight 1. There is no way we can reach 3, so it's -1 for 3.

    Input: V = 6, E = 7, edges = [[0,1,2], [0,4,1], [4,5,4], [4,2,2], [1,2,3], [2,3,6], [5,3,1]]
    Output: [0, 2, 3, 6, 1, 5]
    Explanation: Shortest path from 0 to 1 is 0->1 with edge weight 2.
    Shortest path from 0 to 2 is 0->4->2 with edge weight 1+2=3. Shortest path from 0 to 3 is 0->4->5->3
    with edge weight 1+4+1=6. Shortest path from 0 to 4 is 0->4 with edge weight 1.Shortest path
    from 0 to 5 is 0->4->5 with edge weight 1+4=5.*/

    public int[] shortestPath(int V, int E, int[][] edges) {
        int maxValue = (int) 1e9;//Integer.MAX_VALUE; //use 1e9 to submit on gfg
        ArrayList<ArrayList<Integer[]>> graph = buildDirectedGraph(V,edges);
        ArrayList<Integer> topoSort=topoSort(V,graph);
        int[] distance = new int[V];
        Arrays.fill(distance, maxValue);
        distance[0]=0;
        for(int i=0;i<topoSort.size();i++){
            int node=topoSort.get(i);
            for(Integer[] neighbour:graph.get(node)){
                int currDist = distance[neighbour[0]];
                int newDist=distance[node]+neighbour[1];
                if(newDist<currDist){
                    distance[neighbour[0]]=newDist;
                }
            }
        }

        for(int i=0;i<distance.length;i++){
            if(distance[i]==maxValue)
                distance[i]=-1;
        }
        return distance;

    }

    public static ArrayList<Integer> topoSort(int V, ArrayList<ArrayList<Integer[]>> adjLs) {
        int[] indegree = new int[V];
        Arrays.fill(indegree,0);

        for(int i=0;i<V;i++){
            for(Integer[] neighbour:adjLs.get(i)){
                indegree[neighbour[0]]+=1;
            }
        }

        Queue<Integer> queue=new LinkedList<>();
        for(int i=0;i<V;i++){
            if(indegree[i]==0)
                queue.add(i);
        }

        ArrayList<Integer> topoSort=new ArrayList<>();
        while (!queue.isEmpty()){
            int node=queue.remove();
            topoSort.add(node);
            for(Integer[] neighbour:adjLs.get(node)){
                indegree[neighbour[0]]-=1;
                if(indegree[neighbour[0]]==0)
                    queue.add(neighbour[0]);
            }
        }
        return topoSort;
    }

    public ArrayList<ArrayList<Integer[]>> buildDirectedGraph(int nodes, int[][] edges){
        ArrayList<ArrayList<Integer[]>> adjList = new ArrayList<>();
        for(int i =0;i<nodes;i++){
            adjList.add(i,new ArrayList<>());
        }
        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];
            adjList.get(u).add(new Integer[]{v,edge[2]});
        }
        return adjList;
    }


}
