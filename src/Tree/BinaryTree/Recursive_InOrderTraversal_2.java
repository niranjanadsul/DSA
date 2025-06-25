package Tree.BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class Recursive_InOrderTraversal_2 {
    //https://leetcode.com/problems/binary-tree-inorder-traversal/
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> ls = new ArrayList<>();
        inOrder(root,ls);
        return ls;
    }

    //root,left,right
    public void inOrder(TreeNode node,List<Integer> traversal){
        if(node!=null){
            inOrder(node.left,traversal);
            traversal.add(node.val);
            inOrder(node.right,traversal);
        }
    }
}
