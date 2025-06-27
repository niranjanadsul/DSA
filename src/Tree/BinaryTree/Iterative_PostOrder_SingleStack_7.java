package Tree.BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Iterative_PostOrder_SingleStack_7 {
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> ls = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        ArrayList<TreeNode> visited = new ArrayList<>();

        if(root!=null) {
            st.add(root);
            while(!st.isEmpty()){
                TreeNode top=st.peek();
                if((top.right==null || visited.contains(top.right)) &&
                        (top.left==null || visited.contains(top.left))){
                    ls.add(top.val);
                    visited.add(top);
                    st.pop();
                }else {
                    if (top.right != null) {
                        st.push(top.right);
                    }
                    if (top.left != null) {
                        st.push(top.left);
                    }
                }
            }
        }
        return ls;
    }
}
