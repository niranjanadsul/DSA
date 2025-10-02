package Tree.BinaryTree.TreeOperations_2;

import Tree.BinaryTree.TreeNode;

public class BalancedBinaryTree_2 {
    //https://leetcode.com/problems/balanced-binary-tree/description/

    public boolean isBalanced(TreeNode root) {
        return maxDepth(root)!=-1;
    }
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left = maxDepth(root.left);
        if(left==-1)
            return -1;
        int right = maxDepth(root.right);
        if(right==-1)
            return -1;
        int currHeight= Math.max(left, right)+1;
        int diff=Math.abs(left-right);
        return diff<=1?currHeight:-1;
    }
}
