package Tree.BinaryTree;

public class BuildTree {
    //to build tree call with i=1
    public static TreeNode getNode(int i,int[] arr){
        if(i>arr.length || arr[i-1]==-1)
            return null;
        TreeNode node = new TreeNode(arr[i-1]);
        node.left=getNode(i*2,arr);
        node.right=getNode(i*2+1,arr);
        return node;
    }
}
