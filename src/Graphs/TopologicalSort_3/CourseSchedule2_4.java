package Graphs.TopologicalSort_3;

import java.util.*;

public class CourseSchedule2_4 {
    //https://leetcode.com/problems/course-schedule/description/
    /*There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
     You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that
     you must take course bi first if you want to take course ai.

    For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
    Return true if you can finish all courses. Otherwise, return false.
    Example 1:

    Input: numCourses = 2, prerequisites = [[1,0]]
    Output: true
    Explanation: There are a total of 2 courses to take.
    To take course 1 you should have finished course 0. So it is possible.
    Example 2:

    Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
    Output: false
    Explanation: There are a total of 2 courses to take.
    To take course 1 you should have finished course 0, and to take course 0 you should also have
    finished course 1. So it is impossible.*/

    //this problem is same a topo sort

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<Integer> topoSort=topoSort(numCourses,prerequisites);
        if(topoSort.size()!=numCourses)
            return new int[0];
        Collections.reverse(topoSort);
        return topoSort.stream().mapToInt(Integer::intValue).toArray();
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

        ArrayList<Integer> topoSort=new ArrayList<>();
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
}
