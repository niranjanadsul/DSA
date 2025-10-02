package Tree.BinaryTree.TreeOperations_2.BFS_Problems_10;

import Tree.BinaryTree.TreeNode;

import java.util.*;

public class SerializeAndDeserializeTree_3 {
    //https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
    /*Serialization is the process of converting a data structure or object into a sequence of bits so
    that it can be stored in a file or memory buffer, or transmitted across a network connection link to
    be reconstructed later in the same or another computer environment.
    Design an algorithm to serialize and deserialize a binary tree.
    There is no restriction on how your serialization/deserialization algorithm should work.
    You just need to ensure that a binary tree can be serialized to a string and this string can be
    deserialized to the original tree structure.
    Clarification: The input/output format is the same as how LeetCode serializes a binary tree.
    You do not necessarily need to follow this format, so please be creative and come up with different
    approaches yourself.*/

    //Use BFS to traverse and encode
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String encode = "";
        Queue<TreeNode> q = new LinkedList<>();
        if(root == null)
            return encode;

        q.add(root);
        while(!q.isEmpty()){
            TreeNode curr = q.remove();
            if(curr == null){
                encode+="#,";
            } else{
                encode=encode+curr.val+",";
                q.add(curr.left);
                q.add(curr.right);
            }
        }
        System.out.println(encode);
        //avoid the last comma
        return encode.substring(0,encode.length()-1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.isEmpty())
            return null;

        Queue<String> qs = new LinkedList<>();
        for(String s:data.split(",")){
            qs.add(s+"");
        }

        Queue<TreeNode> qt = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(qs.remove()));
        qt.add(root);
        while(!qt.isEmpty()){
            TreeNode curr = qt.remove();
            if(curr == null)
                continue;
            String left = qs.remove();
            if(!left.equals("#"))
                curr.left = new TreeNode(Integer.parseInt(left));
            String right = qs.remove();
            if(!right.equals("#"))
                curr.right = new TreeNode(Integer.parseInt(right));

            qt.add(curr.left);
            qt.add(curr.right);
        }
        return root;
    }

}
