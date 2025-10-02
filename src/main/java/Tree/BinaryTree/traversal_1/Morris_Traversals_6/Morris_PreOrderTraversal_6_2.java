package Tree.BinaryTree.traversal_1.Morris_Traversals_6;

import Tree.BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Morris_PreOrderTraversal_6_2 {
    //https://leetcode.com/problems/binary-tree-preorder-traversal/

    //this algo is used because it does not need any recursion stack or any extra  data structure
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> pre = new ArrayList<>();
        TreeNode curr = root;
        while(curr != null){
            pre.add(curr.val);
            if(curr.left != null){
                TreeNode left = curr.left;
                TreeNode rightMostOfLeftChild = getRightMostOfLeftChild(left);
                rightMostOfLeftChild.right = curr.right;
                curr = left;
            }else{
                curr = curr.right;
            }
        }
        return pre;
    }

    public TreeNode getRightMostOfLeftChild(TreeNode leftChild){
        TreeNode right = leftChild;
        while(right.right != null){
            right = right.right;
        }
        return right;
    }
}
