package Tree.BinarySearchTree;

import Tree.BinaryTree.TreeNode;

import java.util.ArrayList;

public class KthMinElementInBST_6 {
    //https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
    /*Given the root of a binary search tree, and an integer k,
            return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
    Example 1:
    Input: root = [3,1,4,null,2], k = 1
    Output: 1*/
    public int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> inorder = new ArrayList<>();
        //inorder traversal gives us the sorted values of binary search tree
        inorderTraversal(root,inorder);
        //now return the kth element from the sorted array
        return inorder.get(k-1);
    }

    public void inorderTraversal(TreeNode root, ArrayList<Integer> inorder){
        if(root!=null) {
            inorderTraversal(root.left,inorder);
            inorder.add(root.val);
            inorderTraversal(root.right,inorder);
        }
    }
}
