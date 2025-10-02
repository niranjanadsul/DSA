package Tree.BinaryTree.TreeOperations_2;

import Tree.BinaryTree.TreeNode;

public class SymmetricBinaryTree_6 {
    //https://leetcode.com/problems/symmetric-tree/solutions/
    /*Given the root of a binary tree, check whether it is a mirror of itself
    (i.e., symmetric around its center).*/
    public boolean isSymmetric(TreeNode root) {
        if(root==null)
            return true;
        return symmetry(root.left,root.right);
    }

    //consider two pointer. Left pointer to traverse left subtree and
    // right pointer to traverse right subtree
    public boolean symmetry(TreeNode left,TreeNode right){
        if(left==null && right==null)
            return true;
        if(left==null || right==null)
            return false;
        //                                        LEFT      RIGHT                   RIGHT       LEFT
        return left.val==right.val && symmetry(left.left,right.right) && symmetry(left.right,right.left);
    }

}
