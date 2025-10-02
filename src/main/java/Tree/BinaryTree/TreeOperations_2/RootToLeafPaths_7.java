package Tree.BinaryTree.TreeOperations_2;

import Tree.BinaryTree.Node;

import java.util.ArrayList;

public class RootToLeafPaths_7 {
    //https://www.geeksforgeeks.org/problems/root-to-leaf-paths/1
    /*Given a Binary Tree, you need to find all the possible paths from the root node
    to all the leaf nodes of the binary tree.
    Note: The paths should be returned such that paths from
    the left subtree of any node are listed first, followed by paths from the right subtree.*/
    public static ArrayList<ArrayList<Integer>> Paths(Node root) {
        ArrayList<ArrayList<Integer>> ls = new ArrayList<>();
        traverse(root,new ArrayList<>(),ls);
        return ls;
    }

    public static void traverse(Node node,ArrayList<Integer> currLs,ArrayList<ArrayList<Integer>> ls){
        if(node==null)
            return;
        ArrayList<Integer> list = new ArrayList<>(currLs);
        list.add(node.data);
        if(isLeaf(node)) {
            ls.add(list);
            return;
        }
        traverse(node.left,list,ls);
        traverse(node.right,list,ls);
    }

    public static boolean isLeaf(Node node){
        if(node.left==null && node.right==null)
            return true;
        return false;
    }
}
