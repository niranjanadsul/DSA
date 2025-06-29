package Tree.BinaryTree.traversal;

import Tree.BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Recursive_PreOrderTraversal_1 {
    //https://leetcode.com/problems/binary-tree-preorder-traversal/
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> ls = new ArrayList<>();
        preOrder(root,ls);
        return ls;
    }

    //root,left,right
    public void preOrder(TreeNode node,List<Integer> traversal){
        if(node!=null){
            traversal.add(node.val);
            preOrder(node.left,traversal);
            preOrder(node.right,traversal);
        }
    }
}
