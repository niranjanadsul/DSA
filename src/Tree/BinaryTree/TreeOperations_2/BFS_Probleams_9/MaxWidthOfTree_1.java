package Tree.BinaryTree.TreeOperations_2.BFS_Probleams_9;

import Tree.BinaryTree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class MaxWidthOfTree_1 {
    //https://leetcode.com/problems/maximum-width-of-binary-tree/description/
    /*Given the root of a binary tree, return the maximum width of the given tree.
    The maximum width of a tree is the maximum width among all levels.
    The width of one level is defined as the length between the end-nodes
    (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes that
    would be present in a complete binary tree extending down to that level are also counted into
    the length calculation.
    It is guaranteed that the answer will in the range of a 32-bit signed integer.*/
    public int widthOfBinaryTree(TreeNode root) {
        Queue<Object[]> que=new LinkedList<>();
        //each entry in a queue will contain 3 elements (Node,Index,Level)
        que.add(new Object[]{root,1,1});
        int oldLevel= 0; //initially level will be taken a 0
        int levelStartIndex=0,levelEndIndex=0;
        int maxWidth= Integer.MIN_VALUE;

        while(!que.isEmpty()){
            Object[] obj=que.poll();
            TreeNode node = (TreeNode) obj[0];
            int index=(int)obj[1];
            int currLevel=(int)obj[2];
            if(oldLevel!=currLevel){
                //this is start of new level so we need to store the start Node index for this level
                oldLevel=currLevel;
                levelStartIndex=index;
            }
            levelEndIndex=index;
            maxWidth=Math.max(maxWidth,levelEndIndex-levelStartIndex+1);
            if(node.left!=null)
                que.add(new Object[]{node.left,2*index,currLevel+1});
            if(node.right!=null)
                que.add(new Object[]{node.right,2*index+1,currLevel+1});
        }
        return maxWidth;
    }
}
