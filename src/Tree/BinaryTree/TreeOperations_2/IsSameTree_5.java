package Tree.BinaryTree.TreeOperations_2;

import Tree.BinaryTree.TreeNode;

public class IsSameTree_5 {
    //https://leetcode.com/problems/same-tree/
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //if both the nodes are null then same tree
        if(p==null && q==null)
            return true;
        //check that one of the node is null or if not null then both should have same value
        if((p==null && q!=null) || (p!=null && q==null) || p.val!=q.val)
            return false;

        //if current nodes have same value then check the left and right nodes
        return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }
}
