package Tree.BinaryTree.traversal_1;

import Tree.BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Recursive_PostOrderTraversal_3 {
    //https://leetcode.com/problems/binary-tree-postorder-traversal/description/
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> ls = new ArrayList<>();
        postOrder(root,ls);
        return ls;
    }

    //left,right,root
    public void postOrder(TreeNode node, List<Integer> traversal){
        if(node!=null){
            postOrder(node.left,traversal);
            postOrder(node.right,traversal);
            traversal.add(node.val);
        }
    }
}
