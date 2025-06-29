package Tree.BinaryTree.TreeOperations;

import Tree.BinaryTree.TreeNode;

public class MaxDepthOfBinaryTree_1 {
    //https://leetcode.com/problems/maximum-depth-of-binary-tree/submissions/543270635/
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right)+1;
    }
}
