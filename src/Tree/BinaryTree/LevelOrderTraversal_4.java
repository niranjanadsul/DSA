package Tree.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal_4 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> traversal = new ArrayList<>();
        if(root==null)
            return traversal;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int level=1;
        List<Integer> ls = new ArrayList<>();
        while (!q.isEmpty()){
            if(level==0){
                traversal.add(ls);
                ls=new ArrayList<>();
                level=q.size();
            }
            TreeNode node=q.poll();
            ls.add(node.val);
            level--;
            if(node.left!=null)
                q.add(node.left);
            if(node.right!=null)
                q.add(node.right);
        }
        traversal.add(ls);
        return traversal;
    }

    public static TreeNode getNode(int i,int[] arr){
        if(i>arr.length || arr[i-1]==-1)
            return null;
        TreeNode node = new TreeNode(arr[i-1]);
        node.left=getNode(i*2,arr);
        node.right=getNode(i*2+1,arr);
        return node;
    }

    public static void main(String[] args) {
        LevelOrderTraversal_4 levelOrderTraversal4=new LevelOrderTraversal_4();
        System.out.println(levelOrderTraversal4.levelOrder(getNode(1,new int[]{3,9,20,10,-1,15,7})));
    }
}
