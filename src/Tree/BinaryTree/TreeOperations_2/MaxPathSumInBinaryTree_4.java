package Tree.BinaryTree.TreeOperations_2;

import Tree.BinaryTree.BuildTree;
import Tree.BinaryTree.TreeNode;

public class MaxPathSumInBinaryTree_4 {
    //https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
    /*A path in a binary tree is a sequence of nodes where each pair
    of adjacent nodes in the sequence has an edge connecting them.
    A node can only appear in the sequence at most once.
    Note that the path does not need to pass through the root.
    The path sum of a path is the sum of the node's values in the path.
    Given the root of a binary tree, return the maximum path sum of any non-empty path.
    Input: root = [-10,9,20,null,null,15,7]
    Output: 42
    Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.*/

    int max;
    public int maxPathSum(TreeNode root) {
        this.max=Integer.MIN_VALUE;
        maxDepth(root);
        return this.max;
    }

    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int leftSum = Math.max(maxDepth(root.left),0);
        int rightSum = Math.max(maxDepth(root.right),0);
        //if any of the left and right sums are negative then better not to consider that path
        this.max=Math.max(this.max,leftSum+root.val+rightSum);// path left root right
        return Math.max(leftSum, rightSum)+root.val;
    }

    public static void main(String[] args) {
        MaxPathSumInBinaryTree_4 maxPathSumInBinaryTree4=new MaxPathSumInBinaryTree_4();
        System.out.println(maxPathSumInBinaryTree4.maxPathSum(BuildTree.getNode(1,new int[]{1,-2,3})));
        System.out.println(maxPathSumInBinaryTree4.maxPathSum(BuildTree.getNode(1,new int[]{9,6,-3,-1,-1,-6,2,-1,-1,2,-1,-6,-6,-6})));
    }
}
