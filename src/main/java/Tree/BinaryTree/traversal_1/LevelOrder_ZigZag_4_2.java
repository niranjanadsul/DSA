package Tree.BinaryTree.traversal_1;

import Tree.BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrder_ZigZag_4_2 {
    //https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> traversal = new ArrayList<>();
        if(root==null)
            return traversal;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int level=1;
        boolean reverse=false;//reverse will help us to fill from left(false) or right(true)
        List<Integer> ls = new ArrayList<>();
        while (!q.isEmpty()){
            if(level==0){
                if(reverse)//this conditions helps to make the zigzag pattern
                    ls = ls.reversed();
                traversal.add(ls);
                ls=new ArrayList<>();
                level=q.size();
                reverse=!reverse;
            }
            TreeNode node=q.poll();
            ls.add(node.val);
            level--;
            if(node.left!=null)
                q.add(node.left);
            if(node.right!=null)
                q.add(node.right);
        }
        if(reverse)
            ls = ls.reversed();
        traversal.add(ls);
        return traversal;
    }
}
