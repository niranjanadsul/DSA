package Tree.BinarySearchTree;

import Tree.BinaryTree.TreeNode;

public class ConstructBSTFromPreOrder_9 {
    // https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
    /*Given an array of integers preorder,
    which represents the preorder traversal of a BST (i.e., binary search tree), construct the tree and return its root.
    It is guaranteed that there is always possible to find a binary search tree with
    the given requirements for the given test cases.
    A binary search tree is a binary tree where for every node, any descendant of
    Node.left has a value strictly less than Node.val, and any descendant of Node.right has a value
    strictly greater than Node.val.
    A preorder traversal of a binary tree displays the value of the node first,
    then traverses Node.left, then traverses Node.right.*/

    // this has worst case (Skewed BST) T.C = O(n^2) because for each value we need to traverse n nodes
    // in the BST
    // and best case (Balanced BST) T.C= O(n*log n) for each value we need to traverse
    // the height i.s. log n nodes
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);
        for(int i=1;i<preorder.length;i++)
            insertInBST(preorder[i],root);
        return root;
    }

    public TreeNode insertInBST(int val, TreeNode root){
        if(root==null)
            root= new TreeNode(val);
        if(val<root.val)
            root.left=insertInBST(val,root.left);
        if(root.val<val)
            root.right=insertInBST(val,root.right);
        return root;
    }
}
