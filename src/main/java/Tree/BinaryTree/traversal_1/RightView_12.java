package Tree.BinaryTree.traversal_1;

import Tree.BinaryTree.Node;
import Tree.BinaryTree.TreeNode;

import java.util.*;

public class RightView_12 {
    //https://leetcode.com/problems/binary-tree-right-side-view/description/
    /*Given the root of a binary tree, imagine yourself standing on the right side of it,
    return the values of the nodes you can see ordered from top to bottom.*/
    public List<Integer> rightSideView(TreeNode root) {
        TreeMap<Integer, ArrayList<Integer>>  rowNodesMap = new TreeMap<>();
        ArrayList<Integer> ls= new ArrayList<>();
        traverse(root,0,0,rowNodesMap);
        for(Map.Entry<Integer, ArrayList<Integer>> entry:rowNodesMap.entrySet()){
            ArrayList<Integer> inLs = entry.getValue();
            ls.add(inLs.get(inLs.size()-1));
        }
        return ls;
    }

    public void traverse(TreeNode node, int row, int col, TreeMap<Integer, ArrayList<Integer>> rowNodesMap){
        if(node==null)
            return;
        rowNodesMap.computeIfAbsent(row, x->new ArrayList<>()).add(node.val);
        traverse(node.left,row+1,col-1,rowNodesMap);
        traverse(node.right,row+1,col+1,rowNodesMap);
    }
}
