package Tree.BinarySearchTree;

import Tree.BinaryTree.Node;

public class FindCeil_2 {
    //https://www.geeksforgeeks.org/problems/implementing-ceil-in-bst/1
    /*Given a BST and a number X, find Ceil of X.
    Note: Ceil(X) is a number that is either equal to X or is immediately greater than X.
    If Ceil could not be found, return -1.
    Examples:
    Input: root = [5, 1, 7, N, 2, N, N, N, 3], X = 3
    Output: 3
    Explanation: We find 3 in BST, so ceil of 3 is 3.*/
    int findCeil(Node root, int key) {
        if(root==null)
            return -1;
        if(root.data==key)
            return key;
        int ceil=-1;
        if(key<root.data) {
            ceil=root.data;
            int x=findCeil(root.left, key);
            ceil=x==-1?ceil:x;
        }else{
            ceil=findCeil(root.right,key);
        }
        return ceil;
    }
}
