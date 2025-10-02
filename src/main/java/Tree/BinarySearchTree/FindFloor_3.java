package Tree.BinarySearchTree;

import Tree.BinaryTree.Node;

public class FindFloor_3 {
    //https://www.geeksforgeeks.org/problems/floor-in-bst/1
    /*You are given a BST(Binary Search Tree) with n number of nodes and value x.
    your task is to find the greatest value node of the BST which is smaller than or equal to x.
    Note: when x is smaller than the smallest node of BST then returns -1.*/
    public static int floor(Node root, int key) {
        if(root==null)
            return -1;
        if(root.data==key)
            return key;
        int floor=-1;
        if(root.data<key) {
            floor=root.data;
            int x=floor(root.right, key);
            floor=x==-1?floor:x;
        }else{
            floor=floor(root.left,key);
        }
        return floor;
    }
}
