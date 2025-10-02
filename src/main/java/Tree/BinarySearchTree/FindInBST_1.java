package Tree.BinarySearchTree;

import Tree.BinaryTree.TreeNode;

public class FindInBST_1 {
    //https://leetcode.com/problems/search-in-a-binary-search-tree/description/
    /*You are given the root of a binary search tree (BST) and an integer val.
    Find the node in the BST that the node's value equals val and return the subtree rooted with that node.
     If such a node does not exist, return null.*/
    public TreeNode searchBST(TreeNode root, int val) {
        if(root==null)
            return null;
        if(root.val==val)
            return root;
        TreeNode node=null;
        if(root.val<val)
            node=searchBST(root.right,val);
        else
            node=searchBST(root.left,val);
        return node;
    }
}
