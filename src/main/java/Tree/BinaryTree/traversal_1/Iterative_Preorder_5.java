package Tree.BinaryTree.traversal_1;

import Tree.BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Iterative_Preorder_5 {
    //https://leetcode.com/problems/binary-tree-preorder-traversal/
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> ls = new ArrayList<>();
        Stack<TreeNode> stk=new Stack<>();
        if(root==null)
            return ls;
        stk.push(root);
        while (!stk.isEmpty()){
            TreeNode rootNode=stk.pop();
            ls.add(rootNode.val);
            //now as we want left first we will push right fist and then left
            if(rootNode.right!=null)
                stk.push(rootNode.right);
            if(rootNode.left!=null)
                stk.push(rootNode.left);
        }
        return ls;
    }
}
