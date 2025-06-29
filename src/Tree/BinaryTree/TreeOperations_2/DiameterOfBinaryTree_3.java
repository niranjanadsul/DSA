package Tree.BinaryTree.TreeOperations_2;

import Tree.BinaryTree.TreeNode;

public class DiameterOfBinaryTree_3 {
    //https://leetcode.com/problems/diameter-of-binary-tree/
    //just add the left and right depth at each node and find the max
    int maxDia;
    public int diameterOfBinaryTree(TreeNode root) {
        this.maxDia=0;
        this.maxDepth(root);
        return this.maxDia;
    }
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        this.maxDia=Math.max(this.maxDia,left+right);
        return Math.max(left, right)+1;
    }
}
