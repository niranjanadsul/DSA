package Tree.BinaryTree.TreeOperations_2;

import Tree.BinaryTree.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BurningTree_12 {
    //https://www.geeksforgeeks.org/problems/burning-tree/1
    /*Given a binary tree and a target node,
    determine the minimum time required to burn the entire tree if the target node is set on fire.
    In one second, the fire spreads from a node to its left child, right child, and parent.
    Note: The tree contains unique values.*/
    //this problem uses same approach as findNode at distance k
    public static int minTime(Node root, int target) {
        HashMap<Node,Node> parentMap = new HashMap<>();
        getParentMap(root,null,parentMap);
        return findNodeAtDistanceK(findTargetNode(root,target),new ArrayList<>(),parentMap)-1;
    }

    public static int findNodeAtDistanceK(Node node, List<Node> visited,
                                    HashMap<Node,Node> parentMap){
        if(node==null)
            return 0;
        if(visited.contains(node))
            return 0;//already this node is burnt
        visited.add(node);//burn current node by marking it burnt/visited
        //burn left and right child nodes and parent Node
        int left = findNodeAtDistanceK(node.left,visited,parentMap);
        int right = findNodeAtDistanceK(node.right,visited,parentMap);
        int parent = findNodeAtDistanceK(parentMap.get(node),visited,parentMap);
        //find max time and time to burn current node
        return 1+Math.max(left,Math.max(right,parent));
    }

    public static Node findTargetNode(Node node, int target){
        if(node ==null)
            return null;
        if(node.data==target)
            return node;
        Node left = findTargetNode(node.left,target);
        Node right = findTargetNode(node.right,target);
        return left==null?right:left;
    }

    //method to find each nodes Parent
    public static void getParentMap(Node root, Node parent, HashMap<Node,Node> parentMap){
        if(root==null)
            return;
        parentMap.put(root,parent);
        getParentMap(root.left,root,parentMap);
        getParentMap(root.right,root,parentMap);
    }
}
