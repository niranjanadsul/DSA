package Tree.BinaryTree.traversal_1;

import Tree.BinaryTree.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class BottomView_11 {
    //https://www.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1
    /*Given a binary tree, return an array where elements represent the bottom view of
    the binary tree from left to right.
    Note: If there are multiple bottom-most nodes for a horizontal distance from the root,
    then the later one in the level order traversal is considered.*/
    public ArrayList<Integer> bottomView(Node root) {
        TreeMap<Integer, TreeMap<Integer, ArrayList<Integer>>> columnRowMap = new TreeMap<>();
        ArrayList<Integer> ls= new ArrayList<>();
        traverse(root,0,0,columnRowMap);
        for(Map.Entry<Integer,TreeMap<Integer, ArrayList<Integer>>> entry:columnRowMap.entrySet()){
            TreeMap<Integer, ArrayList<Integer>> mp=entry.getValue();
            //we will get the list for the last in this column as we have applied reverse order
            //we need to select the last node from each row
            for(Map.Entry<Integer, ArrayList<Integer>> e:mp.entrySet()){
                //got last row
                ArrayList<Integer> inLs=e.getValue();
                //get last value from the last row
                ls.add(inLs.get(inLs.size()-1));
                break;
            }
        }
        return ls;
    }

    //need to insert the rows in a reverse order as we are interested in bottom row for each column
    public void traverse(Node node,int row,int col,TreeMap<Integer,TreeMap<Integer, ArrayList<Integer>>> map){
        if(node==null)
            return;
        map.computeIfAbsent(col,x->new TreeMap<>(Collections.reverseOrder())).computeIfAbsent(row, x->new ArrayList<>()).add(node.data);
        traverse(node.left,row+1,col-1,map);
        traverse(node.right,row+1,col+1,map);
    }
}
