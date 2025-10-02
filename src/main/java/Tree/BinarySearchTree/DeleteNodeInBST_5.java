package Tree.BinarySearchTree;

import Tree.BinaryTree.TreeNode;

public class DeleteNodeInBST_5 {
    //https://leetcode.com/problems/delete-node-in-a-bst/description/
    /*Given a root node reference of a BST and a key,
    delete the node with the given key in the BST. Return the root node reference (possibly updated) of
    the BST.
    Basically, the deletion can be divided into two stages:
    Search for a node to remove.
    If the node is found, delete the node.*/
    //edge case: even root can get deleted
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root==null)
            return root;
        if(root.val==key) {
            //the node is found
            //if the node has no child i.e. leaf node we simply return null as the node is deleted
            if(root.left==null && root.right==null)
                return null;
            //if the node has a single child
            //if the node has no left child simply return the right child and vice versa
            if(root.left==null)
                return root.right;
            else if(root.right==null)
                return root.left;
            //if we are here it means the node has both child
            //find the max from left subtree
            TreeNode maxLeft = findMaxFromLeftSubTree(root.left);
            root.val=maxLeft.val;
            root.left=deleteNode(root.left, maxLeft.val);
        } else if(key<root.val) //find and delete in left subtree
            root.left = deleteNode(root.left,key);
        else                    //find and delete in left subtree
            root.right= deleteNode(root.right,key);
        return root;
    }

    public TreeNode findMaxFromLeftSubTree(TreeNode left){
        TreeNode right = left;
        while (right.right!=null)
            right=right.right;
        return right;
    }
}
