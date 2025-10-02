package Tree.BinaryTree.traversal_1;

import Tree.BinaryTree.TreeNode;

import java.util.*;

public class VerticalOrderTraversal_9 {
    //https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
    /*Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
    For each node at position (row, col), its left and right children will be at positions
    (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).*/

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer,TreeMap<Integer, ArrayList<Integer>>> columnRowMap = new TreeMap<>();
        List<List<Integer>> ls= new ArrayList<>();
        traverse(root,0,0,columnRowMap);
        for(Map.Entry<Integer,TreeMap<Integer, ArrayList<Integer>>> entry:columnRowMap.entrySet()){
            TreeMap<Integer, ArrayList<Integer>> mp=entry.getValue();
            ArrayList<Integer> l=new ArrayList<>();
            for(Map.Entry<Integer, ArrayList<Integer>> e:mp.entrySet()){
                ArrayList<Integer> inLs=e.getValue();
                Collections.sort(inLs);
                l.addAll(inLs);
            }
            ls.add(l);
        }
        return ls;
    }

    public void traverse(TreeNode node,int row,int col,TreeMap<Integer,TreeMap<Integer, ArrayList<Integer>>> map){
        if(node==null)
            return;
        map.computeIfAbsent(col,x->new TreeMap<>()).computeIfAbsent(row,x->new ArrayList<>()).add(node.val);
        traverse(node.left,row+1,col-1,map);
        traverse(node.right,row+1,col+1,map);
    }
}
