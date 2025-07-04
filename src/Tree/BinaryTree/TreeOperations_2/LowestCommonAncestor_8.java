package Tree.BinaryTree.TreeOperations_2;

import Tree.BinaryTree.TreeNode;

public class LowestCommonAncestor_8 {
    //https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
    /*Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
    According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between
     two nodes p and q as the lowest node in T that has both p and q as descendants
     (where we allow a node to be a descendant of itself).”*/
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null)//terminating case
            return null;
        //success terminating case
        //if we find p or q then we return p or q
        if(root==p || root==q)
            return root;

        //POST ORDER
        //check if p or q in left subtree of this root
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        //check if p or q in right subtree of this root
        TreeNode right = lowestCommonAncestor(root.right,p,q);

        //if we find p and q means this is the lowest node where p and q split in left and right subtree
        if(left!=null && right!=null)
            return root;

        //if any of the left ot right is null means this is the edge case and the opposite is the LCA
        return left==null?right:left;
    }
}
