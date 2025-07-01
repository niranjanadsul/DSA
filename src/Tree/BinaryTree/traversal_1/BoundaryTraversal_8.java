package Tree.BinaryTree.traversal_1;

import Tree.BinaryTree.Node;

import java.util.ArrayList;

public class BoundaryTraversal_8 {
    //https://www.geeksforgeeks.org/problems/boundary-traversal-of-binary-tree/1
    //we will break this problem into 3 steps
    //Step 1: traversing the left boundary (Refer image BoundaryTraversal_Note_1.png)
        /*Start from the root of the tree.
        Traverse down the left side of the tree until we reach a leaf node. For each non-leaf node,
        add its value to the result list.
        Traverse to its left child. If unavailable, call the recursion function to its right child.*/
    
    //Step 2: traverse all leaf nodes in preOrder fashion (Refer image BoundaryTraversal_Note_2.png)
        /*If the current node is a leaf, add its value to the result list.
        Recursively travel to the current nodes left and right subtrees in a preorder fashion.*/

    //Step 3: traverse the right boundary nodes (Refer image BoundaryTraversal_Note_3.png)
        /*Start from the root's right node
        Traverse to the right most side of the tree until we reach a leaf node.
        For each non-leaf node, call its right child; if unavailable, call left child.
        **************While the recursion backtracks, add the current node’s value to the result list*/
    public boolean isLeafNode(Node node){
        return node.left==null && node.right==null;
    }

    //O(height) == O(log n)
    //start from root.left
    public void traverseLeftBoundary(Node root, ArrayList<Integer> ls){
        //traverse until we reach the first leafNode
        if(!isLeafNode(root)){
            ls.add(root.data); //each non leaf val to add in list
            //Traverse to its left child. If unavailable, call the recursion function to its right child
            if(root.left!=null){
                traverseLeftBoundary(root.left,ls);
            }else{
                traverseLeftBoundary(root.right,ls);
            }
        }
    }

    //start from root.right
    public void traverseRightBoundary(Node root, ArrayList<Integer> ls){
        //traverse until we reach the first leafNode from right
        if(!isLeafNode(root)){
            //Traverse to its right child. If unavailable, call the recursion function to its left child
            if(root.right!=null){
                traverseRightBoundary(root.right,ls);
            }else{
                traverseRightBoundary(root.left,ls);
            }
            ls.add(root.data); //each non leaf val to add in list during the backtracking
        }
    }

    //O(n) as we traverse all nodes
    //insert the leaf nodes in preorder root,left,right
    public void traverseLeafNodes(Node node, ArrayList<Integer> ls){
        if(node==null)
            return;
        if(isLeafNode(node))
            ls.add(node.data);
        else{
            traverseLeafNodes(node.left,ls);
            traverseLeafNodes(node.right,ls);
        }
    }

    ArrayList<Integer> boundaryTraversal(Node root) {
        ArrayList<Integer> ls = new ArrayList<>();
        if(root==null)
            return ls;
        ls.add(root.data);
        if(root.left!=null)
            traverseLeftBoundary(root.left,ls);
        if(!isLeafNode(root)) //because root os already added
            traverseLeafNodes(root,ls);
        if(root.right!=null)
            traverseRightBoundary(root.right,ls);
        return ls;
    }

}
