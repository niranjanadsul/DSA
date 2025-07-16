package Tree.BinarySearchTree;

import Tree.BinaryTree.TreeNode;

public class LCAInBST_8 {
    //https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
    /*Given a binary search tree (BST), find the lowest common ancestor (LCA)
    node of two given nodes in the BST.
    According to the definition of LCA on Wikipedia:
    “The lowest common ancestor is defined between two nodes p and q as the lowest node in T
    that has both p and q as descendants (where we allow a node to be a descendant of itself).”*/
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null)
            return root;
        if(root==p || root==q)
            return root;
        //we have 3 cases
        //both p and q are less than root
        if(p.val<root.val && q.val<root.val) {
            TreeNode curr=lowestCommonAncestor(root.left,p,q);
            return curr==null?root:curr;
        }
        //both p and q are greater then root
        if(root.val<p.val && root.val<q.val) {
            TreeNode curr=lowestCommonAncestor(root.right,p,q);
            return curr==null?root:curr;
        }
        //if we find a node where p.val is less than current node  and q.val is greater than current node
        //this means the current node bifurcates the p and q into 2 sub trees
        //thus current node is LCA
        return root;
    }
}
