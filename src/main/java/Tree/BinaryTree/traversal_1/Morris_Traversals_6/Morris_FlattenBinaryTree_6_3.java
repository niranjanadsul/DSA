package Tree.BinaryTree.traversal_1.Morris_Traversals_6;

import Tree.BinaryTree.TreeNode;

public class Morris_FlattenBinaryTree_6_3 {
    //https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/
    /*Given the root of a binary tree, flatten the tree into a "linked list":

    The "linked list" should use the same TreeNode class where the right child pointer points to the
     next node in the list and the left child pointer is always null.
    The "linked list" should be in the same order as a pre-order traversal of the binary tree.*/
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while(curr != null){
            if(curr.left != null){
                TreeNode left = curr.left;
                TreeNode rightMostOfLeftChild = getRightMostOfLeftChild(left);
                rightMostOfLeftChild.right = curr.right;
                curr.right = left;//move left child to right
                curr.left = null;//remove left reference
            }
            curr = curr.right;
        }
    }

    public TreeNode getRightMostOfLeftChild(TreeNode leftChild){
        TreeNode right = leftChild;
        while(right.right != null){
            right = right.right;
        }
        return right;
    }
}
