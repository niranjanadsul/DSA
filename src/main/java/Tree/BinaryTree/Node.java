package Tree.BinaryTree;

public class Node {
    public int data;
    public Node left;
    public Node right;
    public Node() {}
    public Node(int val) { this.data = val; }
    public Node(int val, Node left, Node right) {
        this.data = val;
        this.left = left;
        this.right = right;
    }
}
