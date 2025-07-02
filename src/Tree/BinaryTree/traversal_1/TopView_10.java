package Tree.BinaryTree.traversal_1;

import Tree.BinaryTree.Node;
import Tree.BinaryTree.TreeNode;

import java.util.*;

public class TopView_10 {
    //https://www.geeksforgeeks.org/problems/top-view-of-binary-tree/1
    /*Input: root[] = [1, 2, 3, N, 4, N, N, N, 5, N, 6]
              1
            /   \
            2     3
             \
              4
                \
                  5
                    \
                      6
    Output: [2, 1, 3, 6]
    Explanation: Node 1 is the root and visible.
            Node 2 is the left child and visible from the left side.
    Node 3 is the right child and visible from the right side.
    Nodes 4, 5, and 6 are vertically aligned, but only the lowest node 6 is visible from the top view.
    Thus, the top view is 2 1 3 6.*/
    //use the vertical order traversal
    //take the first node from each column

    static ArrayList<Integer> topView(Node root) {
        TreeMap<Integer, TreeMap<Integer, ArrayList<Integer>>> columnRowMap = new TreeMap<>();
        ArrayList<Integer> ls= new ArrayList<>();
        traverse(root,0,0,columnRowMap);
        for(Map.Entry<Integer,TreeMap<Integer, ArrayList<Integer>>> entry:columnRowMap.entrySet()){
            TreeMap<Integer, ArrayList<Integer>> mp=entry.getValue();
            for(Map.Entry<Integer, ArrayList<Integer>> e:mp.entrySet()){
                ArrayList<Integer> inLs=e.getValue();
                ls.add(inLs.get(0));
                break;
            }
        }
        return ls;
    }

    public static void traverse(Node node,int row,int col,TreeMap<Integer,TreeMap<Integer, ArrayList<Integer>>> map){
        if(node==null)
            return;
        map.computeIfAbsent(col,x->new TreeMap<>()).computeIfAbsent(row,x->new ArrayList<>()).add(node.data);
        traverse(node.left,row+1,col-1,map);
        traverse(node.right,row+1,col+1,map);
    }
}
