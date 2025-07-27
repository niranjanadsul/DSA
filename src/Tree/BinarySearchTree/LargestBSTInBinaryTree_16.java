package Tree.BinarySearchTree;

import Tree.BinaryTree.Node;

public class LargestBSTInBinaryTree_16 {
    //https://www.geeksforgeeks.org/problems/largest-bst/1
    //refer the question from note
    // TC = O(n)
    static int largestBst(Node root) {
        return (int) findLargestBST(root)[1];
    }

    static Object[] findLargestBST(Node node){
        if(node==null)
            return new Object[]{true,0,Integer.MAX_VALUE,Integer.MIN_VALUE};
        Object[] left = findLargestBST(node.left);
        Object[] right = findLargestBST(node.right);

        //This uses POST order traversal left,right , root

        int currentVal =node.data;
        if((boolean)left[0] && (boolean)right[0] &&
                (int)left[3]<currentVal && currentVal<(int)right[2]) {
                    //left is BST and right is BST and also current node is part of that BST
                    //so increase the count by left count + right count + 1 for current node
                    //get the min from left, currentValue as there might be no left child
                    // and get the max from right , current value as there can be no right child
                    return new Object[]{true, (int) left[1] + (int) right[1] + 1,
                            Math.min(currentVal,(int)left[2]), Math.max(currentVal,(int)right[3])};
        } else{
            //either of the left or right sub tree is not a BST
            //hence the subtree with current node as root cannot be a BST
            return new Object[]{false, Math.max((int) left[1] ,(int) right[1] ), Integer.MAX_VALUE,Integer.MIN_VALUE};
        }
    }
}
