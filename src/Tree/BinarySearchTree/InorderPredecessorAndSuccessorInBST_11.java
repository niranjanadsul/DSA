package Tree.BinarySearchTree;

import Tree.BinaryTree.Node;

import java.util.ArrayList;

public class InorderPredecessorAndSuccessorInBST_11 {
    //https://www.geeksforgeeks.org/problems/predecessor-and-successor/1
    /*You are given root node of the BST and an integer key.
    You need to find the in-order successor and predecessor of the given key.
    If either predecessor or successor is not found, then set it to NULL.
    Note:- In an inorder traversal the number just smaller than the target is the predecessor
    and the number just greater than the target is the successor.
    Examples :

    Input: root[] = [8, 1, 9, N, 4, N, 10, 3, N, N, N], key = 8

    Output: 4 9
    Explanation: In the given BST the inorder predecessor of 8 is 4 and inorder successor of 8 is 9.*/
    public ArrayList<Node> findPreSuc(Node root, int key) {
        Node node= root;
        Node max = null;
        while(node!=null){
            if(node.data<=key)
                node=node.right;
            else {
                if(max==null || max.data> node.data)
                    max = node;
                node=node.left;
            }
        }

        node=root;
        Node min=null;
        while(node!=null){
            if(node.data>=key)
                node=node.left;
            else {
                if(min==null || min.data<node.data)
                    min=node;
                node=node.right;
            }
        }
        ArrayList<Node> ans = new ArrayList<>();
        ans.add(min);
        ans.add(max);
        return ans;
    }
}
