package Tree.BinaryTree.TreeOperations_2.BFS_Problems_10;

import Tree.BinaryTree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class CountNodesInCompleteBinaryTree_2 {
    //https://leetcode.com/problems/count-complete-tree-nodes/description/
    /*Given the root of a complete binary tree, return the number of the nodes in the tree.

    According to Wikipedia, every level, except possibly the last,
    is completely filled in a complete binary tree,
    and all nodes in the last level are as far left as possible.
    It can have between 1 and 2h nodes inclusive at the last level h.
    Design an algorithm that runs in less than O(n) time complexity.*/

    public int countNodes(TreeNode root) {
        if(root==null)
            return 0;
        Queue<Object[]> que = new LinkedList<>();
        que.add(new Object[]{root,1});
        int maxIndex = 1;
        while(!que.isEmpty()){
            Object[] obj=que.poll();
            TreeNode currNode = (TreeNode) obj[0];
            int currIndex = (int)obj[1];
            if(currNode.left==null){
                break;
            }
            que.add(new Object[]{currNode.left,2*currIndex});

            maxIndex=Math.max(maxIndex,2*currIndex);
            if(currNode.right==null){
                break;
            }
            que.add(new Object[]{currNode.right,2*currIndex+1});
            maxIndex=Math.max(maxIndex,2*currIndex+1);
        }
        return maxIndex;
    }
}
