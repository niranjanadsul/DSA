package Tree.BinaryTree.TreeOperations_2.BFS_Problems_10;

import Tree.BinaryTree.TreeNode;

public class CountNodesInCompleteBinaryTree2_3 {
    //this is a recursive solution as the earlier is iterative
    public int countNodes(TreeNode root) {
        if(root==null){
            return 0;
        }
        return countNodes(root.left)+countNodes(root.right)+1;
    }
}
