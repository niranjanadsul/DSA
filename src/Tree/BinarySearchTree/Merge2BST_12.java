package Tree.BinarySearchTree;

import Tree.BinaryTree.Node;
import Tree.BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Merge2BST_12 {
    //https://www.geeksforgeeks.org/problems/merge-two-bst-s/1
    //Given two BSTs, return elements of merged BSTs in sorted form.
    public ArrayList<Integer> merge(Node root1, Node root2) {
        ArrayList<Integer> t1=new ArrayList<>();
        inOrder(root1,t1);
        ArrayList<Integer> t2=new ArrayList<>();
        inOrder(root2,t2);
        ArrayList<Integer> combined=new ArrayList<>();
        mergeSortedArrays(t1,t2,combined);
        return combined;
    }

    public void inOrder(Node node, List<Integer> traversal){
        if(node!=null){
            inOrder(node.left,traversal);
            traversal.add(node.data);
            inOrder(node.right,traversal);
        }
    }

    public void mergeSortedArrays(ArrayList<Integer> t1,ArrayList<Integer> t2,ArrayList<Integer> com){
        int i =0,j=0;
        while(i<t1.size() && j<t2.size()){
            if(t1.get(i)<t2.get(j)) {
                com.add(t1.get(i));
                i++;
            }else{
                com.add(t2.get(j));
                j++;
            }
        }
        while(i<t1.size()){
            com.add(t1.get(i));
            i++;
        }
        while(j<t2.size()){
            com.add(t2.get(j));
            j++;
        }
    }
}
