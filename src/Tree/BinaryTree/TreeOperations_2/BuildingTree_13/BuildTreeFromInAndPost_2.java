package Tree.BinaryTree.TreeOperations_2.BuildingTree_13;

import Tree.BinaryTree.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class BuildTreeFromInAndPost_2 {
    //https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
    /*Given two integer arrays inorder and postorder where inorder is the
    inorder traversal of a binary tree and postorder is the postorder traversal of the same tree,
     construct and return the binary tree.
            Example 1:
    Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
    Output: [3,9,20,null,null,15,7]*/
    //same as building tree with pre and in order with slight difference
    // at each iteration the preEnd will be the root of the current sub tree
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer,Integer> indexMap = new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            indexMap.put(inorder[i],i);
        }
        return createTree(0,postorder.length-1,
                0,inorder.length-1,postorder,inorder,indexMap);
    }

    public TreeNode createTree(int postStart, int postEnd,int inStart, int inEnd,int[] postorder, int[] inOrder, Map<Integer,Integer> indexMap){
        if(postStart>postEnd || inStart>inEnd)//failure termination
            return null;
        int val = postorder[postEnd];// last from the preorder range is the root of the current subTree
        TreeNode root=new TreeNode(val);//create the node out of it

        int rootIndex = indexMap.get(val);//find the index of root node in inorder array
        int leftSubTreeSize = rootIndex-inStart;//find the size of the left sub tree

        //now move on to build left and right subtree using the indexes
        root.left=createTree(postStart,postStart+leftSubTreeSize-1,
                inStart,rootIndex-1,postorder,inOrder,indexMap);
        root.right=createTree(postStart+leftSubTreeSize,postEnd-1,
                rootIndex+1,inEnd,postorder,inOrder,indexMap);
        return root;
    }

    public static void main(String[] args) {
        BuildTreeFromInAndPost_2 buildTreeFromInAndPost2=new BuildTreeFromInAndPost_2();
        buildTreeFromInAndPost2.buildTree(new int[]{9,3,15,20,7},new int[]{9,15,7,20,3});
    }
}
