package Graphs.TopologicalSort_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class FindEventualSafeStates_OptimizedByDFS_6 {
    //same previous problem
    //Optimized by using DFS
    //TC=O(V+E)
    public List<Integer> eventualSafeNodes(int[][] graph) {
        TreeSet<Integer> set = new TreeSet<>();
        HashMap<Integer,Boolean> visited= new HashMap<>();//to avoid cycle
        for(int i=0;i<graph.length;i++){
            dfs(i,set,visited,graph);
        }
        return new ArrayList<>(set);
    }

    public boolean dfs(int v,TreeSet set,HashMap<Integer,Boolean> visited,int[][] graph){
        if(visited.containsKey(v)) {
            return visited.get(v);
        }
        visited.put(v,false);

        for(int j=0;j<graph[v].length;j++){
            if(!dfs(graph[v][j],set,visited,graph))
                return false;
        }
        set.add(v);
        visited.put(v,true);
        return true;
    }

}
