package Graphs;

import java.util.LinkedList;
import java.util.Queue;

public class Network {

    public static void main (String[] args) {
        int V = 12;
        int[][] edge = {{1,5},{1,7},{1,2},{1,4},{3,7},{4,7},{3,5},{0,6},{0,1},{0,4},{2,6},{0,3},{0,2}};

        Network obj = new Network();
        int ans = obj.makeConnected(V, edge);
        System.out.println("The number of operations needed: " + ans);

    }

    public int makeConnected(int n, int[][] connections) {
        if(connections.length<n-1){
            return -1;
        }
        Queue<Node> pq=new LinkedList<>();
        int[] parent=new int[n];
        int[] size=new int[n];
        for(int i=0;i<n;i++){
            parent[i]=i;
            size[i]=1;
        }
        for(int i=0;i<connections.length;i++){

            int[] arr=connections[i];

            int s=arr[0];
            int t=arr[1];
            pq.add(new Node(s,t));
        }


        int count=0;
        int extraEdges=0;

        while(!pq.isEmpty()){
            Node node=pq.poll();
            int u=node.u;
            int v=node.v;

            if(findUParent(u,parent)==findUParent(v,parent)){

                extraEdges++;
            }else{
                unionBySize(u,v,parent,size);
            }

        }

        for(int i=0;i<parent.length;i++){
            if(i==parent[i]){
                count++;
            }
        }
        if(extraEdges>=count-1){
            return count-1;
        }
        return -1;
    }

    public static int findUParent(int node,int[] parent){
        if(parent[node]==node){
            return parent[node];
        }
        return parent[node]=findUParent(parent[node],parent);
    }


    public static void  unionBySize(int u,int v,int[] parent,int[] size){
        int uPS=findUParent(u,parent);
        int uPDest=findUParent(v,parent);
        if(uPS==uPDest){
            return ;
        }
        if(size[uPS]<size[uPDest]){
            parent[uPS]=uPDest;
            size[uPDest]=size[uPDest]+size[uPS];
        }else{
            parent[uPDest]=uPS;
            size[uPS]=size[uPDest]+size[uPS];
        }

    }
}

class Node{
    int u;
    int v;
    public Node(int u,int v){
        this.u =u;
        this.v =v;
    }
}
