package Tree.BinarySearchTree;

import Tree.BinaryTree.TreeNode;

import java.util.HashSet;

public class TwoSunInBST_14 {
    //https://leetcode.com/problems/two-sum-iv-input-is-a-bst/solutions/6966156/easy-solution-recursion/
    /*Given the root of a binary search tree and an integer k, return true if there exist two elements in the BST such that their sum is equal to k, or false otherwise.
    Example 1:
    Input: root = [5,3,6,2,4,null,7], k = 9
    Output: true
    Example 2:
    Input: root = [5,3,6,2,4,null,7], k = 28
    Output: false*/
    public HashSet<Integer> set;
    public TwoSunInBST_14(){
        this.set=new HashSet<>();
    }
    public boolean findTarget(TreeNode root, int k) {
        //the intuition behind this problem is
        //we need to check for a pair with sum k
        //so we will iterate on each node using pre order
        //for each node we will check if the partner node from the pair exists in the hashset
        //i.e. check whether k-node.val exist in the hashset
        //if it does not exist then add this node value to the hashset
        if(root==null)
            return false;
        if(set.contains(k-root.val))
            return true;
        set.add(root.val);
        //check left and  right nodes
        return findTarget(root.left,k) || findTarget(root.right,k);
    }
}
