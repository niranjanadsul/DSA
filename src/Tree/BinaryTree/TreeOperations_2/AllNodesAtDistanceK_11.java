package Tree.BinaryTree.TreeOperations_2;

import Tree.BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllNodesAtDistanceK_11 {
    //https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/description/
    /*Given the root of a binary tree, the value of a target node target,
    and an integer k, return an array of the values of all nodes that have a distance k
    from the target node.
    You can return the answer in any order.*/

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        HashMap<TreeNode,TreeNode> parentMap = new HashMap<>();
        getParentMap(root,null,parentMap);
        List<Integer> ans = new ArrayList<>();
        findNodeAtDistanceK(target,0,k,new ArrayList<>(),parentMap,ans);
        return ans;
    }

    public void findNodeAtDistanceK(TreeNode node,int distance, int k,List<TreeNode> visited,
                                    HashMap<TreeNode,TreeNode> parentMap, List<Integer> ans){
        if(node==null)
            return;
        if(visited.contains(node))
            return;
        if(distance==k) {
            ans.add(node.val);
            return;//any node further will be at k+1 distance
        }
        visited.add(node);
        //check left and right child nodes and parent Node
        findNodeAtDistanceK(node.left,distance+1,k,visited,parentMap,ans);
        findNodeAtDistanceK(node.right,distance+1,k,visited,parentMap,ans);
        findNodeAtDistanceK(parentMap.get(node),distance+1,k,visited,parentMap,ans);
    }

    //method to find each nodes Parent
    public void getParentMap(TreeNode root,TreeNode parent,HashMap<TreeNode,TreeNode> parentMap){
        if(root==null)
            return;
        parentMap.put(root,parent);
        getParentMap(root.left,root,parentMap);
        getParentMap(root.right,root,parentMap);
    }
}
