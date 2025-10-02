package Tree.BinarySearchTree;

import Tree.BinaryTree.TreeNode;

public class RecoverBST_15 {
    //https://leetcode.com/problems/recover-binary-search-tree/description/
    //an inorder traversal of BST gives us the sorted order of nodes
    //thus we need to perform inorder traversal
    //but with each call we also use 3 references
    //one to store the previous node visited during the inorder traversal
    TreeNode prev, first, second;
    public void recoverTree(TreeNode root) {
        prev=null;
        first=null;
        second=null;
        inorder(root);
        if(first!=null && second!=null){
            int x=first.val;
            first.val=second.val;
            second.val=x;
        }
    }

    public void inorder(TreeNode node){
        if(node!=null){
            inorder(node.left);
            //now that we know we have visited previous for current node
            //we should compare the value of current node to its previous node
            if(prev!=null && prev.val>node.val){
                //if we have a previous node and it has greater value then the current node
                // then there is an issue that needs to be addressed
                //store the references of these nodes
                if(first==null){
                    //we have not yet found one of the error node
                    first=prev;
                }
                second=node;
            }
            //if the previous was null i.e. current node is the smallest node/ first node in inorder
            //make the current node as prev and move ahead to right
            prev=node;
            inorder(node.right);
        }
    }
}
