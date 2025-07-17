package Tree.BinarySearchTree;

import Tree.BinaryTree.TreeNode;

public class ConstructBSTFromPreOrder_Optimized_10 {
    //here we use the bounds of node
    //every node in BST has lower bound and upper bound based on its parent node
    //T.C = O(n)
    public int index;
    public TreeNode bstFromPreorder(int[] preorder) {
        this.index=0;
        return buildBST(preorder,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    public TreeNode buildBST(int[] pre, int min, int max){
        //terminating case
        //if index out of bounds of preOrder array OR value at the index is out of range for this node
        if(this.index>=pre.length || pre[this.index]<min || max<pre[this.index])
            return null;

        int val=pre[this.index++];
        //if here then the value is in range
        TreeNode root=new TreeNode(val);
        root.left=buildBST(pre,min,val-1);
        root.right=buildBST(pre,val+1,max);
        return root;
    }
}
