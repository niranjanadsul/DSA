package Tree.BinaryTree.traversal_1;

import Tree.BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Morris_InOrderTraversal_6_1 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> in = new ArrayList<>();
        TreeNode curr = root;
        while(curr != null){
            if(curr.left != null){
                TreeNode left = curr.left;
                TreeNode rightMostOfLeftChild = getRightMostOfLeftChild(left);
                rightMostOfLeftChild.right = curr;
                curr.left = null;
                curr = left;
            }else{
                in.add(curr.val);
                curr = curr.right;
            }
        }
        return in;
    }

    public TreeNode getRightMostOfLeftChild(TreeNode leftChild){
        TreeNode right = leftChild;
        while(right.right != null){
            right = right.right;
        }
        return right;
    }
}
