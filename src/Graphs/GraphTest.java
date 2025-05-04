package Graphs;

import java.util.*;

public class GraphTest {
    public static void main(String[] args){
        // A-->B  A-->C C-->D  B--->E F
        GraphNode a=new GraphNode("A");
        GraphNode b=new GraphNode("B");
        GraphNode c=new GraphNode("C");
        GraphNode d=new GraphNode("D");
        GraphNode e=new GraphNode("E");
        GraphNode f=new GraphNode("F");

        ///A-->B,C
        Map<GraphNode,List<GraphNode>> adjList = new TreeMap<>();
        List<GraphNode> ac=new ArrayList<>();
        ac.add(b);
        ac.add(c);
        adjList.put(a,ac);
        List<GraphNode> bc=new ArrayList<>();
        bc.add(e);
        adjList.put(b,bc);
        List<GraphNode> cc=new ArrayList<>();
        cc.add(d);
        adjList.put(c,cc);
        adjList.put(f,null);

        Map<GraphNode,Boolean> visitedMap = new HashMap<>();
        visitedMap.put(a,false);
        visitedMap.put(b,false);
        visitedMap.put(c,false);
        visitedMap.put(d,false);
        visitedMap.put(e,false);
        visitedMap.put(f,false);

        Queue<GraphNode> q = new LinkedList<>();
        for(Map.Entry<GraphNode,List<GraphNode>> entry:adjList.entrySet()){
            GraphNode currNode = entry.getKey();
            if(!visitedMap.get(currNode)){
                q.add(currNode);
                while (!q.isEmpty()){
                    GraphNode front = q.remove();
                    System.out.println(front.name);
                    visitedMap.put(front,true);
                    if(adjList.get(front)!=null) {
                        for (GraphNode connectedNode : adjList.get(front)) {
                            if (!visitedMap.get(connectedNode)) {
                                q.add(connectedNode);
                            }
                        }
                    }
                }

            }

        }

        return;

    }
}
