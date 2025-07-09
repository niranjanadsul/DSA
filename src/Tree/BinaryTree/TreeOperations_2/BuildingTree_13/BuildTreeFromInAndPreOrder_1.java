package Tree.BinaryTree.TreeOperations_2.BuildingTree_13;

import Tree.BinaryTree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class BuildTreeFromInAndPreOrder_1 {
    //https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
    /*Given two integer arrays preorder and inorder where preorder is the
    preorder traversal of a binary tree and inorder is the inorder traversal of the same tree,
    construct and return the binary tree.
    Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
    Output: [3,9,20,null,null,15,7]
    */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer,Integer> indexMap = new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            indexMap.put(inorder[i],i);
        }
        return createTree(0,preorder.length-1,
                0,inorder.length-1,preorder,inorder,indexMap);
    }

    public TreeNode createTree(int preStart, int preEnd,int inStart, int inEnd,int[] preOrder, int[] inOrder, Map<Integer,Integer> indexMap){
        if(preStart>preEnd || inStart>inEnd)//failure termination
            return null;
        int val = preOrder[preStart];// first from the preorder range is the root of the current subTree
        TreeNode root=new TreeNode(val);//create the node out of it

        int rootIndex = indexMap.get(val);//find the index of root node in inorder array
        int leftSubTreeSize = rootIndex-inStart;//find the size of the left sub tree

        //now move on to build left and right subtree using the indexes
        root.left=createTree(preStart+1,preStart+leftSubTreeSize,
                inStart,rootIndex-1,preOrder,inOrder,indexMap);
        root.right=createTree(preStart+leftSubTreeSize+1,preEnd,
                rootIndex+1,inEnd,preOrder,inOrder,indexMap);
        return root;
    }

}
