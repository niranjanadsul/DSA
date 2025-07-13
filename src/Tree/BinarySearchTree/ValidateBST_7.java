package Tree.BinarySearchTree;

import Tree.BinaryTree.BuildTree;
import Tree.BinaryTree.TreeNode;

import java.util.ArrayList;

public class ValidateBST_7 {
    //https://leetcode.com/problems/validate-binary-search-tree/description/
    /*Given the root of a binary tree, determine if it is a valid binary search tree (BST).
    A valid BST is defined as follows:
    The left subtree of a node contains only nodes with keys less than the node's key.
    The right subtree of a node contains only nodes with keys greater than the node's key.
    Both the left and right subtrees must also be binary search trees.*/
    public boolean isValidBST(TreeNode root) {
        ArrayList<Integer> inorder = new ArrayList<>();
        //inorder traversal gives us the sorted values of binary search tree
        inorderTraversal(root,inorder);
        boolean isValid = true;
        for(int i=1;i<inorder.size();i++){
            if(inorder.get(i-1)>=inorder.get(i)){
                isValid=false;
                break;
            }
        }
        return isValid;
    }

    public void inorderTraversal(TreeNode root, ArrayList<Integer> inorder){
        if(root!=null) {
            inorderTraversal(root.left,inorder);
            inorder.add(root.val);
            inorderTraversal(root.right,inorder);
        }
    }

    public static void main(String[] args) {
        ValidateBST_7 validateBST7=new ValidateBST_7();
        validateBST7.isValidBST(BuildTree.getTreeNode(1,new int[]{2,2,2}));
    }
}
