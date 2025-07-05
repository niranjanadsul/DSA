package Tree.BinaryTree.TreeOperations_2;

import Tree.BinaryTree.BuildTree;
import Tree.BinaryTree.Node;

public class ChildrenSumInBinaryTree_9 {
    //https://www.geeksforgeeks.org/problems/children-sum-parent/1
    /*Given a binary tree having n nodes.
    Check whether all of its nodes have a value equal to the sum of their child nodes.
    Return 1 if all the nodes in the tree satisfy the given properties, else it returns 0.
    For every node, the data value must be equal to the sum of the data values in the left and right children.
     Consider the data value 0 for a NULL child. Also, leaves are considered to follow the property.*/
    public static int isSumProperty(Node root) {
        //we will use PostOrder Traversal for this
        return isSum(root)==-1?0:1;
    }

    public static int isSum(Node root){
        if(root==null)
            return 0;

        int curr=root.data;
        if(isLeaf(root))//if current node is leaf then return current value
            return curr;

        //if any of the left or right child node does not follow the sum property then return -1
        int left = isSum(root.left);
        if(left==-1)
            return -1;
        int right=isSum(root.right);
        if(right==-1)
            return -1;
        //as the left or right child node follow the sum property
        //return the current node value if this node follows the sum property
        if(left + right==root.data)
            return curr;
        return -1;
    }

    public static boolean isLeaf(Node node){
        if(node.left==null && node.right==null)
            return true;
        return false;
    }

    public static void main(String[] args) {
        Node root= BuildTree.getNode(1,new int[]{10,10});
        isSumProperty(root);
    }
}
